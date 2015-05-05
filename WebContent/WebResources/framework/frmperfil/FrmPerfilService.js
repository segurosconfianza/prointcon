var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.service('FrmPerfilService', function($http, $rootScope) {	    	
    	this.id=0;
    	
    	this.getData = function(pageSize, page) {    		    		
    		return $http({
    	        method: 'GET',
    	        url:  WEB_SERVER+'FrmPerfil/listAll.json',
    	        params: {page: page, pageSize: pageSize }
    	     });
    	 }
    	
    	this.getI18n = function() {    		    		
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmI18n/listModulo.json',
    	        params: {modulo: 'FRM_PERFIL,FRM_PERFMODU,FRM_MODUROPE,FRM_MODULO,FRM_ROLEPERM,FRM_APLICACIONES' }
    	     });
    	 }
    	
    	this.insertRecord = function(peficons, pefinomb, pefidesc, pefifecr, pefiesta) {    	
    		
    		data = {peficons: peficons, pefinomb: pefinomb, pefidesc: pefidesc, pefifecr: pefifecr, pefiesta: pefiesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'FrmPerfil/insert',
    	        data: data
    	     });
    	 }
    	
		this.updateRecord = function(peficons, pefinomb, pefidesc, pefifecr, pefiesta) {    	
    		
			data = {peficons: peficons, pefinomb: pefinomb, pefidesc: pefidesc, pefifecr: pefifecr, pefiesta: pefiesta};
    		
			return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'FrmPerfil/update',
    	        data: dataUpdate
    	     });
    	 }
		
		this.deleteRecord = function(peficons, pefinomb, pefidesc, pefifecr, pefiesta) {    	
    		
    		data = {peficons: peficons, pefinomb: pefinomb, pefidesc: pefidesc, pefifecr: pefifecr, pefiesta: pefiesta};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'FrmPerfil/deleteR',
    	        data: data
    	     });
    	 }
		
		//Children
		this.prepForLoad = function(id, pefinomb) {
	        this.id = id;
	        this.pefinomb=pefinomb;
	        this.loadChildren();
    	};
    	
    	this.loadChildren= function() {
            $rootScope.$broadcast('handleBroadcast');
        };
        
    	$rootScope.$broadcast('handleBroadcast');
		
		this.getDataChildren = function(pageSize, page, id) {  
			if(id!=0)
			{
	    		return $http({
	    	        method: 'GET',
	    	        url: WEB_SERVER+'FrmPerfmodu/listAll.json',
	    	        params: {page: page, pageSize: pageSize, pemopefi: id }
	    	     });
			}
    	 };
    	    	
    	this.insertRecordChildren = function(pemocons, pemopefi, pemomoro) {    	
    		
    		data = {pemocons: pemocons, pemopefi: pemopefi, pemomoro: pemomoro};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'FrmPerfmodu/insert',
    	        data: data
    	     });
    	 }
    	
		this.updateRecordChildren = function(pemocons, pemopefi, pemomoro) {    	
    		
			data = {pemocons: pemocons, pemopefi: pemopefi, pemomoro: pemomoro};
    		
			return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'FrmPerfmodu/update',
    	        data: data
    	     });
    	 }
		
		this.deleteRecordChildren = function(pemocons, pemopefi, pemomoro) {    	
    		
			data = {pemocons: pemocons, pemopefi: pemopefi, pemomoro: pemomoro};
    		
    		return $http({
    	        method: 'POST',
    	        url: WEB_SERVER+'FrmPerfmodu/delete',
    	        data: data
    	     });
    	 }
		
		this.getComboMoro = function() {    
    		return $http({
    	        method: 'GET',
    	        url: WEB_SERVER+'FrmPerfmodu/listComboMoro.json'
    	     });
    	 }
    });    	