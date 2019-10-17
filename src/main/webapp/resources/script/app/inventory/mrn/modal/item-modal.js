var MRNItemAddModal = function () {

    /*********************************************************************
     * Init Buttons
     *********************************************************************/

    var initButtons = function () {
        $('#btn-add-mrn-item').on('click', function () {
        	MRNItemAddModal.addMRNItem();
        });
    };

    /*********************************************************************
     * Init Custom Components
     *********************************************************************/
    
    var runMrnItemPartInput = function () {
        $("#itemPartName").inputClear({
            placeholder: "Select a Part",
            btnMethod: "MRNItemAddModal.mrnItemPartModalView()",
            clearMethod: "MRNItemAddModal.aodItemClearStockInput()",
        });
    };

    var runMrnItemStockInput = function () {
        $("#itemStockBatchNo").inputClear({
            placeholder: "Select a Stock",
            btnMethod: "MRNItemAddModal.mrnItemStockModalView()",
        });
    };

    var clearStockInput = function () {
        $('#itemStockId').val("");
        $('#itemStockQuantity').val("");
        $('#itemStockBatchNo').val("");
    }

    /*********************************************************************
     * Init Modals
     *********************************************************************/

    var setModal = function (modalName) {
        this.modalName = modalName;
    };

    var mrnItemPartModalView = function () {

        var bizId = $('#businessId').val();
        if (bizId != null && bizId > 0) {
            var $modal = $('#aod-item-child-modal');
            CustomComponents.ajaxModalLoadingProgressBar();
            setTimeout(function () {
                var url = '../aod/partmodalview';
                $modal.load(url, '', function () {
                    AODPartSelectModal.init(bizId);
                    $modal.modal();
                });
            }, 1000);
        } else {
            alert("Please Select a Business First.");
        }
    	
    };

    var mrnItemStockModalView = function () {

        var partId = $('#itemPartId').val();
        if (partId != null && partId > 0 && partId != undefined) {
            var $modal = $('#aod-item-child-modal');
            CustomComponents.ajaxModalLoadingProgressBar();
            setTimeout(function () {
                var url = '../aod/stockmodalview';
                $modal.load(url, '', function () {
                    AODStockSelectModal.init(partId);
                    $modal.modal();
                });
            }, 1000);
        } else {
            alert("Please Select a Part First. Try Again !");
        }
    	
    };


    /*********************************************************************
     * Init Validator
     *********************************************************************/
    
    var initValidator = function () {

        var form = $('#frm_aod_item');
        var errorHandler = $('.errorHandler', form);
        var successHandler = $('.successHandler', form);

        jQuery.validator.addMethod("greaterThanGRNQuantity", function (value, element) {
            return value > 0
        }, "This item quantity unable to return for seleted AOD");

        form.validate({
            errorElement: "span", // contain the error msg in a span tag
            errorClass: 'help-block',
            errorPlacement: function (error, element) { // render error placement for each input type
                if (element.attr("type") == "radio" || element.attr("type") == "checkbox") { // for chosen elements, need to insert the error after the chosen container
                    error.insertAfter($(element).closest('.form-group').children('div').children().last());
                } else if (element.attr("name") == "dd" || element.attr("name") == "mm" || element.attr("name") == "yyyy" || element.closest('.input-group').has('.input-group-btn').length || element.closest('.form-group').has('.input-group-addon').length) {
                    error.insertAfter($(element).closest('.form-group').children('div'));
                } else if (element.closest('.form-group').has('.select2').length) {
                    error.insertAfter($(element).closest('.form-group').children('span'));
                } else {
                    error.insertAfter(element);
                }
            },
            ignore: "",
            rules: {
                aodItemPartName: {
                    required: true
                },
                itemStockBatchNo: {
                    required: true
                },
                itemQuantity: {
                    required: true,
                    number: true,
                    greaterThanGRNQuantity: [],
                    remote: {
                        url: "../validate/stock/onhandqty",
                        type: "GET",
                        data: {
                            itemStockId: function () {
                                return $('#itemStockId').val()
                            }
                        }
                    }
                }
            },

            messages: {
                aodItemPartName: "Please Select a Part for AOD Item.",
                itemStockBatchNo: "Please Select a Stock.",
                itemQuantity: {
                    required: "Please Insert Item Quantity.",
                    greaterThanGRNQuantity: "0 is not valid for AOD Item Qunatity.",
                    remote: "Not Available Quantity in Stock."
                }
            },
            invalidHandler: function (event, validator) { //display error alert on form submit
                successHandler.hide();
                errorHandler.show();
            },
            highlight: function (element) {
                $(element).closest('.help-block').removeClass('valid');
                // display OK icon
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error').find('.symbol').removeClass('ok').addClass('required');
                // add the Bootstrap error class to the control group
            },
            unhighlight: function (element) { // revert the change done by highlight
                $(element).closest('.form-group').removeClass('has-error');
                // set error class to the control group
            },
            success: function (label, element) {
                label.addClass('help-block valid');
                // mark the current input as valid and display OK icon
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success').find('.symbol').removeClass('required').addClass('ok');
            },
            submitHandler: function (form) {
                successHandler.show();
                errorHandler.hide();
                return true;
            }
        });
    };

    var addMRNItem = function () {
        if ($('#frm_mrn_item').valid()) { 
        	var aodItem = mrnItemObj(); 
            if (!isItemAlreadyAdd(aodItem)) { 
            	MRNItemTab.addItemToList(aodItem);  
                $('#'+ this.modalName).modal('toggle');  
                
            } else {
                alert('Item already Added. Please change it');
            } 
        }
    };  
    
    var mrnItemObj = function() {
    	var mrnItems = {}
    	
    	mrnItems['index'] = CustomValidation.nullValueReplace($("#itemIndex").val());
		mrnItems['id'] = CustomValidation.nullValueReplace($("#itemId").val());
		mrnItems['stockId'] = CustomValidation.nullValueReplace($("#itemStockId").val());
        mrnItems['stockBatchNo'] = CustomValidation.nullValueReplace($("#itemStockBatchNo").val());
        mrnItems['partId'] = CustomValidation.nullValueReplace($("#itemPartId").val());
        mrnItems['partName'] = CustomValidation.nullValueReplace($("#itemPartName").val());
    	mrnItems['partCode'] = CustomValidation.nullValueReplace($("#aodItemPartCode").val());
    	mrnItems['warehouseId'] = CustomValidation.nullValueReplace($("#itemStockWarehouseId").val());
		mrnItems['warehouseName'] = CustomValidation.nullValueReplace($("#itemStockWarehouseName").val()); 
		mrnItems['description'] = CustomValidation.nullValueReplace($("#itemDescription").val());
		mrnItems['itemQuantity'] = CustomValidation.nullValueReplace($("#itemQuantity").val()); 
		mrnItems['remainingQuantity'] = CustomValidation.nullValueReplace($("#itemStockOnHandQty").text()); 
		mrnItems['version'] = CustomValidation.nullValueReplace($("#itemVersion").val()); 
		
		return mrnItems;
	};

    var isItemAlreadyAdd = function (item) {
        for (var i = 0; i < mrnItemList.length; i++) {
            if (mrnItemList[i].index != item.index) {
                if (item.partId == mrnItemList[i].partId) {
                    if (item.stockId == mrnItemList[i].stockId) {
                        return true;
                    }
                }
            }
        }
        return false;
    };

    return {

        init: function (modalName) {
            setModal(modalName);
            runMrnItemPartInput();
            runMrnItemStockInput();
            initButtons();
            initValidator()
        },

        addMRNItem: function () {
            addMRNItem();
        },

        mrnItemPartModalView: function () {
            mrnItemPartModalView();
        },

        mrnItemStockModalView: function () {
            mrnItemStockModalView();
        },

        mrnItemClearStockInput: function () {
            clearStockInput();
        }

    }
}();