var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('FrmTransaccionService', function($http, $rootScope) {	    	
    	this.id=0;
    	
    	this.getData = function(pageSize, page) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'FrmTransaccion/listAll.json',
    	        params: {page: page, pageSize: pageSize }
    	     });
    	 }
    	
    	this.getI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'FRM_TRANSACCION' }
    	     });
    	 }    	
		
		//Children
		this.prepForLoad = function(id, pefinomb) {
	        this.id = id;
	        this.pefinomb=pefinomb;
	        this.loadChildren();
    	};
    	
    	this.loadChildren= function() {
            $rootScope.$broadcast('handleBroadcast');
        };
        
    	$rootScope.$broadcast('handleBroadcast');
		
		this.getDataChildren = function(pageSize, page, id) {  
			if(id!=0)
			{
	    		return $http({
	    	        method: 'GET',
	    	        url: WEB_SERVER+'FrmPerfmodu/listAll.json',
	    	        params: {page: page, pageSize: pageSize, pemopefi: id }
	    	     });
			}
    	 }
		
		
    });    	