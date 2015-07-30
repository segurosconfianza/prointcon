var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('PilUsuaanalisisService', function($http, $rootScope, $routeParams) {	    	
		this.id=0;
		this.usuausua='';
		this.I18n;
    	
    	this.getData = function(pageSize, page, order, filter) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'PilUsua/listAll.json',
    	        params: {pageSize: pageSize, page: page, order: order, filter: filter }
    	     });
    	 }
    	
    	this.loadI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'PIL_USUA,PIL_USUSUCU' }
    	     });
    	 }
    	
    	this.setI18n = function(I18n) {
            this.I18n = I18n;
    	};
    	
    	this.getI18n = function() {
            return this.I18n;
    	};
    	
    	this.insertRecord = function(usuacons,usuaunit,usuadive,usuatiin,usuarazo,usuanomb,usuaapel,usuaemai,usuatele,usuapeco,usuausua,usuapass,usuatipo,usuasucu,usuaesta) {    	
    		
    		data = {usuacons : usuacons, usuaunit : usuaunit, usuadive : usuadive, usuatiin : usuatiin, usuarazo : usuarazo, usuanomb : usuanomb, usuaapel : usuaapel, usuaemai : usuaemai, usuatele : usuatele, usuapeco : usuapeco, usuausua : usuausua, usuapass : usuapass, usuatipo : usuatipo, usuasucu : usuasucu, usuaesta : usuaesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsua/insert',
    	        data: data
    	     });
    	 }
    	
    	this.validateUser = function(username) {    	
    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'PilUsua/validateUser',
    	        params: { username: username},
    	     });
    	 }

		this.updateRecord = function(usuacons,usuaunit,usuadive,usuatiin,usuarazo,usuanomb,usuaapel,usuaemai,usuatele,usuapeco,usuausua,usuapass,usuatipo,usuasucu,usuaesta) {    	
    		
			data = {usuacons : usuacons, usuaunit : usuaunit, usuadive : usuadive, usuatiin : usuatiin, usuarazo : usuarazo, usuanomb : usuanomb, usuaapel : usuaapel, usuaemai : usuaemai, usuatele : usuatele, usuapeco : usuapeco, usuausua : usuausua, usuapass : usuapass, usuatipo : usuatipo, usuasucu : usuasucu, usuaesta : usuaesta};
    		
			return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsua/update',
    	        data: data
    	     });
    	 }
		
		this.deleteRecord = function(usuacons,usuaunit,usuadive,usuatiin,usuarazo,usuanomb,usuaapel,usuaemai,usuatele,usuapeco,usuausua,usuapass,usuatipo,usuasucu,usuaesta) {    	
    		
    		data = {usuacons : usuacons, usuaunit : usuaunit, usuadive : usuadive, usuatiin : usuatiin, usuarazo : usuarazo, usuanomb : usuanomb, usuaapel : usuaapel, usuaemai : usuaemai, usuatele : usuatele, usuapeco : usuapeco, usuausua : usuausua, usuapass : usuapass, usuatipo : usuatipo, usuasucu : usuasucu, usuaesta : usuaesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsua/delete',
    	        data: data
    	     });
    	 }
		
		this.getCombo = function(tablcodi) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmTablas/listByTablcodi.json',
    	        params: {tablcodi: tablcodi }
    	     });
    	 }
		
		this.getSucursales = function(consultaId) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/listComboDynamic.json',
    	        params: {conscons: consultaId}
    	     });
    	 }
			
		//Children
		this.prepForLoadI18n = function() {	                                
	        $rootScope.$broadcast('handleBroadcastUsusucuI18n');
	    }
	        	
		this.prepForLoad = function(id, usuausua) {
	        this.id = id;
	        this.usuausua=usuausua;
	        this.loadChildren();
		}
		
		this.loadChildren= function() {
	        $rootScope.$broadcast('handleBroadcastUsusucu');
	    }
	    
	    this.getDataUsusucu = function(pageSize, page, order, filter) { 
			return $http({
				method: 'GET', 
		        url: WEB_SERVER+'PilUsusucu/listAll.json',
		        params: { pageSize: pageSize, page: page, order: order, filter: filter},
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
