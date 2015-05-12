var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('FrmTransaccionService', function($http, $rootScope) {	    	
    	this.id=0;
    	this.I18n;
    	
    	this.getData = function(pageSize, page) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'FrmTransaccion/listAll.json',
    	        params: {page: page, pageSize: pageSize }
    	     });
    	 }
    	
    	this.loadI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'FRM_TRANSACCION,FRM_LOG,FRM_AUDITORIA,SOP_MOTIVO,SOP_ADJUNTO' }
    	     });
    	 }    	    	

    	this.setI18n = function(I18n) {
	        this.I18n = I18n;
    	};
    	
    	this.getI18n = function() {
	        return this.I18n;
    	};
    	
		//Children
    	this.prepForLoadI18n = function() {	                                
            $rootScope.$broadcast('handleBroadcastLogI18n');
            $rootScope.$broadcast('handleBroadcastAuditoriaI18n');
            $rootScope.$broadcast('handleBroadcastSoporteI18n');
        };
            	
		this.prepForLoad = function(id) {
	        this.id = id;
	        this.loadChildren();
    	};
    	
    	this.loadChildren= function() {
            $rootScope.$broadcast('handleBroadcastLog');
            $rootScope.$broadcast('handleBroadcastAuditoria');
            $rootScope.$broadcast('handleBroadcastSoporte');
        };
            	
		this.getDataLog = function(pageSize, page, id) {  
			if(id!=0)
			{
	    		return $http({
	    	        method: 'GET',
	    	        url: WEB_SERVER+'FrmLog/listAll.json',
	    	        params: {page: page, pageSize: pageSize, slogtran: id }
	    	     });
			}
    	 }
		
		this.getDataAuditoria = function(pageSize, page, id) {  
			if(id!=0)
			{
	    		return $http({
	    	        method: 'GET',
	    	        url: WEB_SERVER+'FrmAuditoria/listAll.json',
	    	        params: {page: page, pageSize: pageSize, auditran: id }
	    	     });
			}
    	 }
		
		this.getDataSoporte = function(pageSize, page, id) {  
			if(id!=0)
			{
	    		return $http({
	    	        method: 'GET',
	    	        url: WEB_SERVER+'SopMotivo/listAll.json',
	    	        params: {page: page, pageSize: pageSize, motitran: id }
	    	     });
			}
    	 }
		
    });    	