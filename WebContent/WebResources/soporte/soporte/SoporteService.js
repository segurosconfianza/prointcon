var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('SoporteService', function($http, $rootScope, $routeParams) {	    	
    	    	    	
    	this.getParams = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'FrmParametro/params.json',
    	        params: {paracons: $routeParams.soporteId}
    	     });
    	 }    	    	
    	
    	this.loadRecord = function(params) {    	
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/loadRecord.json',
    	        params: {conscons: $routeParams.soporteId, params: params}
    	     });
    	 }    			
		
    	
    	this.updateRecord = function(params, paramsData) {    	
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/updateRecord.json',
    	        params: {conscons: $routeParams.soporteId, params: params, paramsData: paramsData}
    	     });
    	 }
    	
    	this.getCombo = function(consultaId) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/listComboDynamic.json',
    	        params: {conscons: consultaId}
    	     });
    	 }
    });    	