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
		    	    	
    	this.updateRecord = function(formData, motidesc) {    	
    		return $http({
    	        method: 'POST', 
    	        url: WEB_SERVER+'FrmConsulta/updateRecord.json',
    	        data: formData,
    	        params: {conscons: $routeParams.soporteId, motidesc: motidesc},
    	        transformRequest: angular.identity,
                headers: {'Content-Type': undefined, 'Content-Transfer-Encoding': 'utf-8'}
    	     });
    	 }
    	
    	this.getCombo = function(consultaId) {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/listComboDynamic.json',
    	        params: {conscons: consultaId}
    	     });
    	 }
    	
    	this.loadData = function() {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmConsulta/loadData.json',
    	        params: {conscons: $routeParams.soporteId}
    	     });
    	 }
    });    	