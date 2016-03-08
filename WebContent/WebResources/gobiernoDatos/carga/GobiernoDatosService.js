var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('GobiernoDatosService', function($http, $rootScope, $routeParams) {	    	
    	
	this.loadData = function() {    
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FrmConsulta/loadData.json',
	        params: {conscons: $routeParams.soporteId}
	     });
	 }
	
	this.getCombo = function(consultaId) {    
		return $http({
	        method: 'GET',
	        url: WEB_SERVER+'FrmConsulta/listComboDynamic.json',
	        params: {conscons: consultaId}
	     });
	 }
	
	this.getParams = function() {    		    		
		return $http({
	        method: 'GET',
	        url:  WEB_SERVER+'FrmParametro/params.json',
	        params: {paracons: $routeParams.soporteId}
	     });
	 } 
	
	this.executeProcess = function(formData) {    	
		return $http({
	        method: 'POST', 
	        url: WEB_SERVER+'GodCarga/updateRecord.json',
	        params: {conscons: $routeParams.soporteId},
	        data: formData,    	        
	        transformRequest: angular.identity,
            headers: {'Content-Type': undefined, 'Content-Transfer-Encoding': 'utf-8'}
	     });

	 }
});    	