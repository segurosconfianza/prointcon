var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('PilUsuaService', function($http) {	    	
    	this.id=0;
    	
    	this.getData = function(pageSize, page, order, filter) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'PilUsua/listAll.json',
    	        params: {pageSize: pageSize, page: page, order: order, filter: filter }
    	     });
    	 }
    	
    	this.getI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'PIL_USUA' }
    	     });
    	 }
    	
    	this.insertRecord = function(usuacons,usuaunit,usuadive,usuatiin,usuarazo,usuanomb,usuaapel,usuaemai,usuatele,usuapeco,usuausua,usuapass,usuatipo,usuasucu,usuaesta) {    	
    		
    		data = {usuacons : usuacons, usuaunit : usuaunit, usuadive : usuadive, usuatiin : usuatiin, usuarazo : usuarazo, usuanomb : usuanomb, usuaapel : usuaapel, usuaemai : usuaemai, usuatele : usuatele, usuapeco : usuapeco, usuausua : usuausua, usuapass : usuapass, usuatipo : usuatipo, usuasucu : usuasucu, usuaesta : usuaesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'PilUsua/insert',
    	        data: data
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
				
    });    	
