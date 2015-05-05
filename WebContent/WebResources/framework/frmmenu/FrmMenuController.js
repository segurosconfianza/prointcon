FrmMainApp.controller('FrmMenuController', ['$scope', 'FrmMenuService', function ($scope, FrmMenuService) {
	    	$scope.oneAtATime = false;
	    		    	
	    	FrmMenuService.getData().then(function(dataResponse) {
	            $scope.menu = dataResponse.data;
	        });
	    	
	    	$scope.selectedItem = {};

	        $scope.options = {};
	        
	        $scope.toggle = function(scope) {
	          scope.toggle();
	        };	        
	      
	        $scope.go = function ( path ) {
	        	window.open(path, "contenido");
	        };
	        	
	        $scope.whatClassIsIt= function(scope, hijos, durl){
	        	var clase;
	            if((hijos==null || hijos.length<1) && durl!=null)
	            	clase="glyphicon-list-alt";
	            else if(scope.collapsed)
	            	clase="glyphicon-folder-open";
	            else
	            	clase="glyphicon-folder-close";
	            return clase;
	        }
	        
	        $scope.urlVerify= function(url){
	        	if(url!=undefined){
		        	var result=url.match(/http/g);
		            if(result!=null && result=="http")
		            	return 1;
	        	}
            	return 0;
	        }
	        
	        $scope.modelVerify= function(model){
	        	if(model!=undefined || model!=null){		        	
		            return model;
	        	}
            	return "modelOwn";
	        }
    	}]);