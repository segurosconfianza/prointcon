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
	        params: {modulo: 'FMT_FORMREGI,FMT_AUDITORIA,FMT_ESTADO,FMT_ADJUNTO,PIL_USUA,PIL_MOTIVO,PIL_MOTIFORM,FRM_TRANSACCION' }
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
	
	this.cancelarRecord = function(forecons) {    	
		
		return $http({
	        method: 'POST',
	        url: WEB_SERVER+'FmtFormregi/cancelarRecord.json',
	        params: {forecons: forecons}
	     });
	 }

	//Children
	this.prepForLoadI18n = function() {	                                
        $rootScope.$broadcast('handleBroadcastAuditoriaI18n');
        $rootScope.$broadcast('handleBroadcastEstadoI18n');
        $rootScope.$broadcast('handleBroadcastAdjuntoI18n');
        $rootScope.$broadcast('handleBroadcastMotiformI18n');
    };
        	
	this.prepForLoad = function(id) {
        this.id = id;
        this.loadChildren();
	};
	
	this.loadChildren= function() {
        $rootScope.$broadcast('handleBroadcastAuditoria');
        $rootScope.$broadcast('handleBroadcastEstado');
        $rootScope.$broadcast('handleBroadcastAdjunto');
        $rootScope.$broadcast('handleBroadcastMotiform');
    };
    
    this.getDataMotiform = function(pageSize, page, order, filter) { 
		return $http({
			method: 'GET', 
	        url: WEB_SERVER+'PilMotiform/listAll.json',
	        params: { pageSize: pageSize, page: page, order: order, filter: filter},
	     });
    } 
    
    this.getDataAuditoria = function(pageSize, page, order, filter) {  		
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FmtAuditoria/listAllFormregi.json',
	        params: {pageSize: pageSize, page: page, order: order, filter: filter, forecons: this.id }
	     });		
	 }
    
    this.getDataEstado = function(pageSize, page, order, filter) {  		
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FmtEstado/listAll.json',
	        params: {pageSize: pageSize, page: page, order: order, filter: filter}
	     });
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
    
    this.aprobarRecord = function(forecons) {    		    		
		return $http({
	        method: 'POST',
	        url:  WEB_SERVER+'FmtFormregi/aprobarRecord.json', 
	        params: {forecons: forecons}
	     });
	 } 
    
    this.getMotivos = function() {    		    		
		return $http({
	        method: 'GET',
	        url:  WEB_SERVER+'PilMotivo/listAll.json', 
	        params: {pageSize: 0, page: 0}
	     });
	 } 
    
    this.devolverRecord = function(mofofore, mofodevo, mofodesc) {    	
		
		data = {mofofore : mofofore, mofodevo : mofodevo, mofodesc : mofodesc};
		
		return $http({
	        method: 'POST',
	        url: WEB_SERVER+'PilMotiform/insertDevolucion',
	        data: data
	     });
	 }
        
});    	