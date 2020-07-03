var StockAgeDetailTable = function () {

    //
    // Pipelining function for DataTables. To be used to the `ajax` option of DataTables
    //
    $.fn.dataTable.pipeline = function (opts) {
        // Configuration options
        var conf = $.extend({
            pages: 5,     // number of pages to cache
            url: '',      // script url
            data: null,   // function or object with parameters to send to the server
                          // matching how `ajax.data` works in DataTables
            method: 'GET' // Ajax HTTP method
        }, opts);

        // Private variables for storing the cache
        var cacheLower = -1;
        var cacheUpper = null;
        var cacheLastRequest = null;
        var cacheLastJson = null;

        return function (request, drawCallback, settings) {
            var ajax = false;
            var requestStart = request.start;
            var drawStart = request.start;
            var requestLength = request.length;
            var requestEnd = requestStart + requestLength;

            if (settings.clearCache) {
                // API requested that the cache be cleared
                ajax = true;
                settings.clearCache = false;
            }
            else if (cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper) {
                // outside cached data - need to make a request
                ajax = true;
            }
            else if (JSON.stringify(request.order) !== JSON.stringify(cacheLastRequest.order) ||
                JSON.stringify(request.columns) !== JSON.stringify(cacheLastRequest.columns) ||
                JSON.stringify(request.search) !== JSON.stringify(cacheLastRequest.search)
            ) {
                // properties changed (ordering, columns, searching)
                ajax = true;
            }

            // Store the request for checking next time around
            cacheLastRequest = $.extend(true, {}, request);

            if (ajax) {
                // Need data from the server
                if (requestStart < cacheLower) {
                    requestStart = requestStart - (requestLength * (conf.pages - 1));

                    if (requestStart < 0) {
                        requestStart = 0;
                    }
                }

                cacheLower = requestStart;
                cacheUpper = requestStart + (requestLength * conf.pages);

                request.start = requestStart;
                request.length = requestLength * conf.pages;

                // Provide the same `data` options as DataTables.
                if ($.isFunction(conf.data)) {
                    // As a function it is executed with the data object as an arg
                    // for manipulation. If an object is returned, it is used as the
                    // data object to submit
                    var d = conf.data(request);
                    if (d) {
                        $.extend(request, d);
                    }
                }
                else if ($.isPlainObject(conf.data)) {
                    // As an object, the data given extends the default
                    $.extend(request, conf.data);
                }

                settings.jqXHR = $.ajax({
                    "type": conf.method,
                    "url": conf.url,
                    "data": request,
                    "dataType": "json",
                    "cache": false,
                    "success": function (json) {
                        cacheLastJson = $.extend(true, {}, json);

                        if (cacheLower != drawStart) {
                            json.data.splice(0, drawStart - cacheLower);
                        }
                        if (requestLength >= -1) {
                            json.data.splice(requestLength, json.data.length);
                        }

                        drawCallback(json);
                    }
                });
            }
            else {
                json = $.extend(true, {}, cacheLastJson);
                json.draw = request.draw; // Update the echo for each response
                json.data.splice(0, requestStart - cacheLower);
                json.data.splice(requestLength, json.data.length);

                drawCallback(json);
            }
        }
    };

    // Register an API method that will empty the pipelined data, forcing an Ajax
    // fetch on the next draw (i.e. `table.clearPipeline().draw()`)
    $.fn.dataTable.Api.register('clearPipeline()', function () {
        return this.iterator('table', function (settings) {
            settings.clearCache = true;
        });
    });

    var initTableByDays = function () {
    	
        var tableName = "tbl_stock_age_by_days";
        
        $('#' + tableName).dataTable().fnDestroy();
        
        var oTable = $('#' + tableName).dataTable({
            processing: true,
            serverSide: true,
            ajax: $.fn.dataTable.pipeline({
                url: "../../restapi/report/stockage/searchDetail?" + $('#frmReportStockAge').serialize(),
                pages: 5
            }),
            columns: [{
                orderable: false,
                searchable: false,
                width: "2%",
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            }, {
                data: 'stockAgeStockNo',
                searchable: false,
                orderable: false,
            }, {
                data: 'stockAgePartCode',
                searchable: false,
                orderable: false
                        }, {
                data: 'stockAgePartDescription',
                searchable: false,
                orderable: false,
            }, {
                data: 'stockAgeRemainQty',
                searchable: false,
                orderable: false,
            },{
            	data: 'stockAgePartUnitPrice',
                searchable: false,
                orderable: false,
            },{
            	data:'stockAgeStockQty',
                searchable: false,
                orderable: false,
            }, {
                data: 'stockAgeCreatedDate',
                render: function(data, type, row, meta) {
                    return toDate(row.stockAgeCreatedDate);
                },
                searchable: false,
                orderable: false
            }, {
                data: 'stockAgeYears',
                searchable: false,
                orderable: false,
                       },{
            	data:'stockAgeMonths',
                searchable: false,
                orderable: false,
                     },{
            	data:'stockAgeDays',
                searchable: false,
                orderable: false,
                   },{
            	data:'stockAgeTotalAmount',
                searchable: false,
                orderable: false,
            } ],
            oLanguage: {
                sLengthMenu: "Show _MENU_ Rows",
                sSearch: "",
                oPaginate: {
                    sPrevious: "&laquo;",
                    sNext: "&raquo;"
                }
            },
            searching: false,
            aaSorting: [
                [2, 'asc']
            ],
            // set the initial value
            iDisplayLength: 10,
            sPaginationType: "full_numbers",
            sPaging: 'pagination'
        });
        $('#' + tableName + '_wrapper .dataTables_length select').addClass("m-wrap small");
        $('#' + tableName + '_wrapper .dataTables_length select').select2();
    };
    
    

    var toDate = function (longDate) {
        if (longDate != null) {
            return moment(longDate).format("DD-MM-YYYY");
        }
        return "";
    };

    return {
    	
    	initTableByDays: function () {
    		initTableByDays();
        },

    };
}();