var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('SoporteChildController', ['$scope', 'SoporteChildService', '$filter', '$upload', function($scope, SoporteChildService, $filter, $upload) {
	
	$scope.Params = {};
	$scope.Result = false;
	$scope.Boton = true;	
	$scope.BotonLoader = false;
	$scope.options={};	
	$scope.paramsSend={};	
	$scope.consconsChild=0;
	
	SoporteChildService.loadConsChield().then(function(dataResponse) {  
		if(dataResponse.data.error!=undefined)
    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
    	else{
    		$scope.consconsChild=dataResponse.data;
    		
    		SoporteChildService.loadData($scope.consconsChild).then(function(dataResponse) {  
    			if(dataResponse.data.error!=undefined)
    	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
    	    	else{
    	    		$scope.title=dataResponse.data.titulo;
    	    		$scope.description=dataResponse.data.descri;
    	    	}
    		});
    		
    		SoporteChildService.getParams($scope.consconsChild).then(function(dataResponse) {  
    	    	
    	    	if(dataResponse.data.error!=undefined)
    	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
    	    	else{
    	    		$scope.columns=dataResponse.data.data;
    	    		//recorro los campos para cargar los data de los combos
    	    		for(i=0; i<$scope.columns.length;i++){    			
    	    			//si el tipo de dato es columna
    	    			if($scope.columns[i].paratida=='CS' || $scope.columns[i].paratida=='CI'){    				    				    				
    	    				//se pasa el codigo del combo
    	    				SoporteChildService.getCombo($scope.columns[i].paracomb).then(function(dataResponse) {    					    					
    	    					if(dataResponse.data.error!=undefined)
    	    			    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
    	    			    	else{    			   
    	    			    		//se carga la data en los options
    	    			    		$scope.options[dataResponse.data.combo] = dataResponse.data.data;      			    		
    	    			    	}
    	    				});    				    				 		    				    		
    	    			}
    	    		}    		
    	    	}
    	    });
    	}
	});
						
	//Evento del calendario
	$scope.open = function($event,opened) {
		
	    $event.preventDefault();
	    $event.stopPropagation();	    
	    
	    $scope[opened] = true;
	  }
	  
	//evento de carga de datos
	$scope.loadRecord= function(){				
			
		$scope.BotonLoader=true;
		$scope.Boton=false;
		$scope.Result= false;		
		
		var verify=true;		
		$scope.paramsSend={};	
		for(i=0; i<$scope.columns.length;i++){
			if($scope.columns[i].paratipo=='E' ){
				//Tomar solo los datos de entrada para enviarlos a la consulta
				$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
				//Verificar si los datos requeridos cumplen con haber sido digitados
				if(!formData.$valid && ($scope.Params[$scope.columns[i].paranomb]==undefined || $scope.Params[$scope.columns[i].paranomb]=='')){
					verify=false;
					break;
				}
			}
		}
				
		if(verify){									
			SoporteChildService.loadRecord($scope.consconsChild, $scope.paramsSend).then(function(dataResponse) {
										
				if(dataResponse.data.error!=undefined){
					$scope.Result=false;										
	    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error); 
	    			$scope.BotonLoader=false;
				}
	        	else{ 	        			        	
	        		$scope.Data=dataResponse.data.data[0];
	        		$scope.Camp=dataResponse.data.camp;
	        		
	        		$scope.Result=true;
	        		$scope.BotonLoader=false;	 	        			        		
	        	}
				$scope.Boton = true;
				
	        }); 						
		}else{
			alert("Datos vacios o incorrectos: Favor diligencie todos los campos");
			$scope.BotonLoader=false;
			$scope.Boton = true;
		}							
	}
	
	
 }            
])