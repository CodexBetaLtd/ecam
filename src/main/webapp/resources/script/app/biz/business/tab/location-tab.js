var TabLocation = function () {
    
    /********************************************
     * Initialize InputClear Components
     ********************************************/

    function initInputClearComponents(){
        initInputClearCountry()
    };
    
    function initInputClearCountry(){
        $("#countryName").inputClear({
            placeholder:"Please specify a country",
            btnMethod:"TabLocation.addCountry()",
        });
    };

    /********************************************
     * Initialize modal views
     ********************************************/
    
    function initModalCountrySelect() {
        var $modal = $('#common-modal');
        CustomComponents.ajaxModalLoadingProgressBar();
        setTimeout(function () {
            var url = '../business/view/modal/countries';
            $modal.load(url, '', function () {
                DatatableModalCountries.init(
                        "common-modal",
                        "tbl_countries",
                        "../restapi/country/tabledata",
                        "setData"
                );
                $modal.modal();
            });
        }, 1000);
    };

    return {
        init: function () {
            initInputClearComponents();
        },
        
        addCountry: function () {
            initModalCountrySelect(); 
        }
    };
}();