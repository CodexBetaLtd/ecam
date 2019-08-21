var AssetBrandSelectModal = function(){
	
    var setAssetBrand  = function(id,name){
    	$('#brandId').val(id);
    	$('#brandName').val(name);
    }

	var saveAssetBrand = function() {
    	
    	var $modal = $('#brand-add-modal');
    	var form = $('#assetBrandAddForm');
    	if (form.valid()) {
    		$.ajax({
    			url : form.attr('action') + '?module=asset',
    			type : 'POST',
    			data : form.serialize(),
    			success : function(response) {
    				$modal.empty().append(response);
    				AssetBrandDataTable.runDataTable();
    				$modal.modal();
    			},
    			error : function(response) { }
    		});
    	}
    };
    
    var assetBrandAddView = function () {
    	var $modal = $('#brand-add-modal');
        CustomComponents.ajaxModalLoadingProgressBar();
    	setTimeout(function () {
    		var url = '../part/brand-add-view';
    		$modal.load(url, '', function (){
    		    AssetBrandAdd.init();
    			$modal.modal();
    		});
    	}, 1000);
    };
    
    var assetBrandView = function () {
    	var $modal = $('#common-modal');
        CustomComponents.ajaxModalLoadingProgressBar();
    	setTimeout(function () {
    		var url = '../part/brand-select-view';
    		$modal.load(url, '', function () {
    			AssetBrandDataTable.runDataTable();
    			$modal.modal();
    		});
    	}, 1000);
    };
    
    return {
        
    	assetBrandView: function(){
    		assetBrandView();
    	},
    	
    	assetBrandAddView: function(){
    		assetBrandAddView();
    	},
    	
    	saveAssetBrand: function() {
        	saveAssetBrand();
        },
        
	    setAssetBrand: function(id,name){
	    	setAssetBrand(id,name);
	    	$('#common-modal').modal('toggle');
	    },
	     
    };
	
}();

