var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('SoporteController', ['$scope', 'SoporteService', '$filter', function($scope, SoporteService, $filter) {
	
	$scope.Params = {};
	$scope.Result = false;
	$scope.Boton = true;	
	$scope.BotonLoader = false;
	$scope.options={};	
	$scope.paramsSend={};	
	 
	SoporteService.getParams().then(function(dataResponse) {  
    	
    	if(dataResponse.data.error!=undefined)
    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
    	else{
    		$scope.columns=dataResponse.data.data;
    		//recorro los campos para cargar los data de los combos
    		for(i=0; i<$scope.columns.length;i++){    			
    			//si el tipo de dato es columna
    			if($scope.columns[i].paratida=='CS' || $scope.columns[i].paratida=='CI'){    				    				    				
    				//se pasa el codigo del combo
    				SoporteService.getCombo($scope.columns[i].paracomb).then(function(dataResponse) {    					    					
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
			SoporteService.loadRecord($scope.paramsSend).then(function(dataResponse) {
										
				if(dataResponse.data.error!=undefined){
					$scope.Result=false;										
	    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error); 
	    			$scope.BotonLoader=false;
				}
	        	else{ 	        			        	
	        		$scope.Params=dataResponse.data.data[0];
	        		$scope.Result=true;
	        		$scope.BotonLoader=false;	        			        		
	        	}
				$scope.Boton = true;
				
				console.log("SALIDA: "+$scope.Params['FECDOC']);
	        }); 						
		}else{
			alert("Datos vacios o incorrectos: Favor diligencie todos los campos");
			$scope.BotonLoader=false;
			$scope.Boton = true;
		}							
	}
	
	//evento de modificar datos
	$scope.updateRecord= function(){				
		
		$scope.BotonLoader=true;
		$scope.Boton=false;		

		var verify=true;		
		$scope.paramsSend={};	
		$scope.paramsSendData={};
		for(i=0; i<$scope.columns.length;i++){
			if($scope.columns[i].paratipo=='E' ){	
				//Tomar solo los datos de entrada para enviarlos a la consulta
				$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
				//Verificar si los datos requeridos cumplen con haber sido digitados
				if(!formData.$valid && ($scope.Params[$scope.columns[i].paranomb]==undefined || $scope.Params[$scope.columns[i].paranomb]=='' || ($scope.Params[$scope.columns[i].paranomb])==' ')){
					verify=false;
					break;
				}
			} else if($scope.columns[i].paratipo=='S' ){
				//Tomar solo los datos de salida para enviarlos a la consulta
				if($scope.Params[$scope.columns[i].paranomb]==undefined)
					$scope.paramsSendData[$scope.columns[i].paranomb]=' ';
				else if($scope.columns[i].paratida=='D')//date
					$scope.paramsSendData[$scope.columns[i].paranomb]=$filter('date')(new Date($scope.Params[$scope.columns[i].paranomb]), 'dd/MM/yyyy');
				else if($scope.columns[i].paratida=='T')//timestamp
					$scope.paramsSendData[$scope.columns[i].paranomb]=$filter('date')(new Date($scope.Params[$scope.columns[i].paranomb]), 'dd/MM/yyyy HH:mm:ss');					
				else	
					$scope.paramsSendData[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
			}
		}					
		
		if(verify){									
			SoporteService.updateRecord($scope.paramsSend, $scope.paramsSendData).then(function(dataResponse) {
										
				if(dataResponse.data.error!=undefined){
	    			alert(dataResponse.data.tituloError+': '+dataResponse.data.error); 
				}
	        	else{ 	        			        	
	        		alert('Proceso Terminado Satisfactoriamente');
	        	}
				$scope.BotonLoader=false;
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