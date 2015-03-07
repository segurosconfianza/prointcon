var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('SoporteChildService', function($http, $rootScope, $routeParams) {	    	
    	  
		this.loadConsChield = function() {    	
			return $http({
		        method: 'GET',
		        url: WEB_SERVER+'FrmConsulta/loadConsChield.json',
		        params: {conscons: $routeParams.soporteId}
		     });
		 } 
	
    	this.getParams = function(conscons) {
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'FrmParametro/params.json',
    	        params: {paracons: conscons}
    	     });
    	 }    	    	
    	
    	this.loadRecord = function(conscons, params) {    	
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/loadRecord.json',
    	        params: {conscons: conscons, params: params}
    	     });
    	 }    					    	    	    	
    	
    	this.getCombo = function(consultaId) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/listComboDynamic.json',
    	        params: {conscons: consultaId}
    	     });
    	 }
    	
    	this.loadData = function(conscons) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/loadData.json',
    	        params: {conscons: conscons}
    	     });
    	 }
    });    	