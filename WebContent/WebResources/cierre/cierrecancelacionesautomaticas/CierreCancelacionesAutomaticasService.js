var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('CierreCancelacionesAutomaticasService', function($http, $rootScope, $routeParams) {	    	
    	    	    	
    	this.getParams = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'FrmParametro/params.json',
    	        params: {paracons: $routeParams.soporteId}
    	     });
    	 }    	    	    	    	 		
		    	    	
    	this.ExecuteProcess = function(formData) {    	
    		return $http({
    	        method: 'POST', 
    	        url: WEB_SERVER+'CierreCancelaciones/ExecuteProcessCierreCancelacionesAuto.json',
    	        data: formData,
    	        params: {conscons: $routeParams.soporteId},
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
    	
    	this.ExecuteProcessCancel = function(formData) {    	
    		return $http({
    	        method: 'POST', 
    	        url: WEB_SERVER+'CierreCancelaciones/ExecuteProcessCreateCancelaciones.json',
    	        data: formData,
    	        params: {conscons: $routeParams.soporteId},
    	        transformRequest: angular.identity,
                headers: {'Content-Type': undefined, 'Content-Transfer-Encoding': 'utf-8'}
    	     });
    	 }
    });    	