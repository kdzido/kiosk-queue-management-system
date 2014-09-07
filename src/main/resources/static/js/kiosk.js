$( document ).ready(function() {
	
	function renderRequestTicketSuccess(data) {
		$('#main').empty();	
		$('#main').append('<p class="bg-success">Your ticket ID is: ' + data.ticketId + '</p>');
	}
	
	function renderRequestTicketForm() {
		var output = _.template($('#requestTicket').text());
		$('#main').append(output);

        $.ajax({
            url: "/qms/queues/",
            type: 'GET',
            contentType: false,
            cache: false,
            processData:false,
            success: function(data, textStatus, jqXHR) {
                if (data.length > 0) {
                    $categories = $("#queueCategory");
                    $.each(data, function() {
                        $categories.append($("<option/>").val(this.queueId).text(this.category));
                    });

                    $(".selectQueue").show();
                    $(".requestButton").show();
                } else {
                    $(".selectQueue").hide();
                    $(".requestButton").hide();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
            }
        });


		$('#requestTicketForm').submit(function(e) {
            var queueId = $('#queueCategory option:selected').val();
			var formObj = $(this);
		    var formURL = '/qms/queues/' + queueId + '/requestTicket'
            var formData = JSON.stringify({ "queueId": new String(queueId) })

		    $.ajax({
                type: 'POST',
		    	url: formURL,
		        data:  formData,
		        contentType: "application/json; charset=utf-8",
                dataType: "json",
		        cache: false,
		        processData:false,
		        success: function(data, textStatus, jqXHR) {
		        	renderRequestTicketSuccess(data);
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        }         
		    });
		    
		    e.preventDefault(); // Prevent Default action.
		});
	}


    function renderQueueTableRequestTicketSuccess(data) {
        $('#infobox').empty();
        $('#infobox').append('<p class="bg-success">Your ticket ID is: ' + data.ticketId + '</p>');
        $('#infobox').show();
        $('#infobox').delay(3000).fadeOut();
    }

    function renderQueueTable(queues) {
		var tableBody = $('#queueTableBody');
		var rowTemplate = $('#queueTableRow').text();

		_.each(queues, function(queue) {
			var row = _.template(rowTemplate, { 
											   'queueId': queue.queueId,
											   'name': queue.name,
											   'category': queue.category,
											   'awaitingNo': queue.awaitingNo,
											   'lastTicketId': (queue.lastTicketId ? queue.lastTicketId : "--"),
											   'consultantName': queue.consultantName
            });
			tableBody.append(row);					
		});
		
		$('#queueTableBody button').click(function(event) {
			var queueId = $(event.target).parents('tr').find('td:nth-child(1)').text();
            var formURL = '/qms/queues/' + queueId + '/requestTicket'
            var formData = JSON.stringify({ "queueId": new String(queueId) })

			$.ajax({
                type: 'POST',
				url: formURL,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: formData,

                success: function(data, textStatus, jqXHR) {
                    renderQueueTableRequestTicketSuccess(data)
				},
				error: function() {
					$('#errorinfobox').show();
					$('#errorinfobox').delay(1000).fadeOut();
				}
			});
			
		});
		
		$.ajax({
	    	url: "/qms/queues",
	    	type: 'GET',
	        contentType: false,
	        cache: false,
	        processData:false,
	        success: function(data, textStatus, jqXHR) {
	        	if (data.length > 0) {
	        		for (i = 0; i < queues.length; i++) {
	        			$categories = $("#" + queues[i].queueId);
	        			$.each(data, function() {
	        				$categories.append($("<option/>").val(this.deviceId).text(this.deviceId));
	        			});
	        		}
        			$(".selectQueue").show();
        			$(".requestButton").show();
	        	} else {
        			$(".selectQueue").hide();
        			$(".requestButton").hide();
	        	}
	        	
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        }         
	    });
	}
	
	function renderQueueList() {
		var output = _.template($('#queueList').text());
		$('#main').append(output);
		$('#infobox').hide();
		$('#errorinfobox').hide();
		
		$.ajax({
	    	url: "/qms/queues",
	    	type: 'GET',
	        contentType: false,
	        cache: false,
	        processData:false,
	        success: function(data, textStatus, jqXHR) {
	        	renderQueueTable(data);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	        }         
	    });

	}
	
	renderRequestTicketForm();

	$('#requestButton').click(function() {
		$('#listButton').removeClass('active');
		$('#requestButton').addClass('active');
		$('#main').empty();
		$('h1').text('Request ticket');
		renderRequestTicketForm();
	});

    $('#listButton').click(function() {
        $('#listButton').addClass('active');
        $('#requestButton').removeClass('active');
        $('#main').empty();
        $('h1').text('Queue list');
        renderQueueList();
    });

});