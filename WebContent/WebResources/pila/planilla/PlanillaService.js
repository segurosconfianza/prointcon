var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('PlanillaService', function($http, $rootScope, $routeParams) {	    	
    	   
	this.id=0;
	this.I18n;
	
	this.getSucursales = function() {    		    		
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'PilUsusucu/listSucursales.json',
	     });
	 } 
	
	this.loadI18n = function() {    		    		
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FrmI18n/listModulo.json',
	        params: {modulo: 'FMT_FORMREGI,FMT_AUDITORIA,FMT_ESTADO,FMT_ADJUNTO,PIL_USUA' }
	     });
	 } 
	
	this.setI18n = function(I18n) {
        this.I18n = I18n;
	};
	
	this.getI18n = function() {
        return this.I18n;
	};
	
	this.loadData = function() {    
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FmtFormato/loadData.json',
	        params: {formcons: $routeParams.PlanillaId}
	     });
	 }
	
	this.getParams = function(vefocons) {    		    		
		return $http({
	        method: 'GET',
	        url:  WEB_SERVER+'FmtCampo/campos.json', 
	        params: {campvefo: vefocons}
	     });
	 } 
	
	this.getData = function(campos, vefocons, pageSize, page, order, filter) { 
			return $http({
				method: 'GET', 
		        url: WEB_SERVER+'FmtFormregi/loadFormatosAdmin.json',
		        params: {vefocons: vefocons, pageSize: pageSize, page: page, order: order, filter: filter},
		     });
	 }    			
	
	this.getCombo = function(consultaId) {    
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FrmConsulta/listComboDynamic.json',
	        params: {conscons: consultaId}
	     });
	 }
	
	this.getTbtablas = function(tablcodi) {    
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FrmTablas/listByTablcodi.json',
	        params: {tablcodi: tablcodi}
	     });
	 }
	
	//Children
	this.prepForLoadI18n = function() {	                                
        $rootScope.$broadcast('handleBroadcastAuditoriaI18n');
        $rootScope.$broadcast('handleBroadcastEstadoI18n');
        $rootScope.$broadcast('handleBroadcastAdjuntoI18n');
    };
        	
	this.prepForLoad = function(id) {
        this.id = id;
        this.loadChildren();
	};
	
	this.loadChildren= function() {
        $rootScope.$broadcast('handleBroadcastAuditoria');
        $rootScope.$broadcast('handleBroadcastEstado');
        $rootScope.$broadcast('handleBroadcastAdjunto');
    };
    
    this.getDataAuditoria = function(pageSize, page, id) {  
		if(id!=0)
		{
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FmtAuditoria/listAll.json',
    	        params: {page: page, pageSize: pageSize, forecons: id }
    	     });
		}
	 }
    
    this.getDataEstado = function(pageSize, page, id) {  
		if(id!=0)
		{
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FmtEstado/listAll.json',
    	        params: {page: page, pageSize: pageSize, forecons: id}
    	     });
		}
	 }
    
    this.getDataAdjunto = function(id) {  
		if(id!=0)
		{
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FmtAdjunto/listAdjunto.json',
    	        params: {forecons: id },
    	        responseType: 'arraybuffer',
    	        headers: { 'Accept': 'application/pdf' },
    	     });
		}
	 }
});    	