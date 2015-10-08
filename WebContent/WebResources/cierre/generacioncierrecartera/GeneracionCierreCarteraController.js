var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('GeneracionCierreCarteraController', ['$scope', 'GeneracionCierreCarteraService', '$filter', function($scope, Service, $filter) {
	
	$scope.Params = {};
	$scope.Result = false;
	$scope.Boton = true;	
	$scope.BotonLoader = false;
	$scope.Error = false;
	$scope.options={};	
	$scope.paramsSend={};	
	
	$scope.init = function() {
		Service.loadData().then(function(dataResponse) {  
			if(dataResponse.data.error!=undefined)
				$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.title=dataResponse.data.titulo;
	    		$scope.description=dataResponse.data.descri;
	    	}
		});
		
		Service.getParams().then(function(dataResponse) {  
	    	
	    	if(dataResponse.data.error!=undefined)
	    		$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.columns=dataResponse.data.data;
	    		//recorro los campos para cargar los data de los combos
	    		for(i=0; i<$scope.columns.length;i++){    			
	    			//si el tipo de dato es columna
	    			if($scope.columns[i].paratida=='CS' || $scope.columns[i].paratida=='CI'){    				    				    				
	    				//se pasa el codigo del combo
	    				Service.getCombo($scope.columns[i].paracomb).then(function(dataResponse) {    					    					
	    					if(dataResponse.data.error!=undefined)
	    						$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
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
	
	//Evento del calendario
	$scope.open = function($event,opened) {
		
	    $event.preventDefault();
	    $event.stopPropagation();	    
	    
	    $scope[opened] = true;
	  }
	  	
	//evento de modificar datos
	$scope.ExecuteProcess= function(){				
		$scope.trans=false;
		$scope.BotonLoader=true;
		$scope.Boton=false;		
		$scope.Error = false;
		
		var verify=true;		
		$scope.paramsSend={};	

		for(i=0; i<$scope.columns.length;i++){
			if($scope.columns[i].paratipo=='E' ){					
				//Verificar si los datos requeridos cumplen con haber sido digitados
				if(($scope.Params[$scope.columns[i].paranomb]==undefined || $scope.Params[$scope.columns[i].paranomb]=='') && $scope.Params[$scope.columns[i].paranomb]!=0){
					verify=false;
					break;
				}
				
				//Tomar solo los datos de salida para enviarlos a la consulta
				if($scope.Params[$scope.columns[i].paranomb]==undefined){
					$scope.paramsSend[$scope.columns[i].paranomb]=null;
				}
				else if($scope.columns[i].paratida=='D'){//date
					if(typeof $scope.Params[$scope.columns[i].paranomb]=="string"){
						//console.log("string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
					}
					else{
						//console.log("no string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$filter('date')(new Date($scope.Params[$scope.columns[i].paranomb]), 'dd/MM/yyyy');
					}
				} else if($scope.columns[i].paratida=='T'){//timestamp
					if(typeof $scope.Params[$scope.columns[i].paranomb]=="string"){
						//console.log("string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
					}
					else{
						//console.log("no string");
						$scope.paramsSend[$scope.columns[i].paranomb]=$filter('date')(new Date($scope.Params[$scope.columns[i].paranomb]), 'dd/MM/yyyy HH:mm:ss');
					}
				} else	{
					$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
				}
			}
			
		}					
		
		if(verify){		
						
			formData=new FormData();
			
			formData.append("params", angular.toJson($scope.paramsSend));
						
			Service.ExecuteProcess(formData).then(function(dataResponse) {
				 	      
        		if(dataResponse.data.Success!=undefined){
        			$scope.trans=true;
        			$scope.sendAlert('Proceso Terminado Satisfactoriamente');
        			
        			$scope.transaccion=dataResponse.data.Success;
        			
        		}
        		else{
        			$scope.Error = true;
        			$scope.sendAlert('Proceso no termino Satisfactoriamente');        			
        			$scope.DescripcionError = dataResponse.data.Eror;        		        			
				}
	        	
				$scope.BotonLoader=false;
				$scope.Boton = true;	
	        }); 						
		}else{ 
			$scope.sendAlert("Datos vacios o incorrectos: Favor diligencie todos los campos");
			$scope.BotonLoader=false;
			$scope.Boton = true;	
		}				
	}
	
	$scope.sendAlert = function(error){
		$scope.$broadcast('loadDataError', error);
	}
 }            
])