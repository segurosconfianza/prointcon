var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('CieEstaprocService', function($http) {	    	
    	this.id=0;
		this.I18n;
    	
    	this.getData = function(pageSize, page, order, filter) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'CieEstaproc/listAll.json',
    	        params: {pageSize: pageSize, page: page, order: order, filter: filter }
    	     });
    	 }
    	
    	this.getI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'CIEESTAPROC' }
    	     });
    	 }
    	
    	this.setI18n = function(I18n) {
            this.I18n = I18n;
    	};
    	
    	this.insertRecord = function(esprcons,esprnomb,esprporc,esprdesc,espreror,espruser,espresta,esprduho,esprfein,esprfefi) {    	
    		
    		data = {esprcons : esprcons, esprnomb : esprnomb, esprporc : esprporc, esprdesc : esprdesc, espreror : espreror, espruser : espruser, espresta : espresta, esprduho : esprduho, esprfein : esprfein, esprfefi : esprfefi};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'CieEstaproc/insert',
    	        data: data
    	     });
    	 }
    	
		this.updateRecord = function(esprcons,esprnomb,esprporc,esprdesc,espreror,espruser,espresta,esprduho,esprfein,esprfefi) {    	
    		
			data = {esprcons : esprcons, esprnomb : esprnomb, esprporc : esprporc, esprdesc : esprdesc, espreror : espreror, espruser : espruser, espresta : espresta, esprduho : esprduho, esprfein : esprfein, esprfefi : esprfefi};
    		
			return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'CieEstaproc/update',
    	        data: data
    	     });
    	 }
		
		this.deleteRecord = function(esprcons,esprnomb,esprporc,esprdesc,espreror,espruser,espresta,esprduho,esprfein,esprfefi) {    	
    		
    		data = {esprcons : esprcons, esprnomb : esprnomb, esprporc : esprporc, esprdesc : esprdesc, espreror : espreror, espruser : espruser, espresta : espresta, esprduho : esprduho, esprfein : esprfein, esprfefi : esprfefi};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'CieEstaproc/delete',
    	        data: data
    	     });
    	 }
				
    });    	
