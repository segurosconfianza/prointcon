var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('PilSucuranalisisService', function($http, $rootScope, $routeParams) {	    	
		this.id=0;
		this.sucunomb='';
		this.I18n;
    	
    	this.getData = function(pageSize, page, order, filter) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'OsiSucursal/listAll.json',
    	        params: {pageSize: pageSize, page: page, order: order, filter: filter }
    	     });
    	 }
    	
    	this.loadI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'OSI_SUCURSAL,PIL_USUA,PIL_USUSUCU' }
    	     });
    	 }
    	
    	this.setI18n = function(I18n) {
            this.I18n = I18n;
    	};
    	
    	this.getI18n = function() {
            return this.I18n;
    	};    	    	
		
		this.getConsulta = function(consultaId) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/listCombo.json',
    	        params: {conscons: consultaId}
    	     });
    	 }
			
		//Children
		this.prepForLoadI18n = function() {	                                
	        $rootScope.$broadcast('handleBroadcastUsuasucurI18n');
	    }
	        	
		this.prepForLoad = function(id, sucunomb) {
	        this.id = id;
	        this.sucunomb=sucunomb;
	        this.loadChildren();
		}
		
		this.loadChildren= function() {
	        $rootScope.$broadcast('handleBroadcastUsuasucur');
	    }
	    
	    this.getDataChild = function(pageSize, page, order, filter) { 
			return $http({
				method: 'GET', 
		        url: WEB_SERVER+'PilUsusucu/listAllAnalistas.json',
		        params: { pageSize: pageSize, page: page, order: order, filter: filter},
		     });
	    }
	    
	    this.getCombo = function(tablcodi) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmTablas/listByTablcodi.json',
    	        params: {tablcodi: tablcodi }
    	     });
    	 }
	    
	    this.insertRecordChild = function(ussucons,ussuusua,ussusucu,ussuesta) {    	
    		
    		data = {ussucons : ussucons, ussuusua : ussuusua, ussusucu : ussusucu, ussuesta : ussuesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsusucu/insert',
    	        data: data
    	     });
    	 }
    	
		this.updateRecordChild = function(ussucons,ussuusua,ussusucu,ussuesta) {    	
    		
			data = {ussucons : ussucons, ussuusua : ussuusua, ussusucu : ussusucu, ussuesta : ussuesta};
    		
			return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsusucu/update',
    	        data: data
    	     });
    	 }
		
		this.deleteRecordChild = function(ussucons,ussuusua,ussusucu,ussuesta) {    	
    		
    		data = {ussucons : ussucons, ussuusua : ussuusua, ussusucu : ussusucu, ussuesta : ussuesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsusucu/delete',
    	        data: data
    	     });
    	 }
    });    	
