var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('GobiernoDatosController', ['$scope', 'GobiernoDatosService', '$filter', '$upload', function($scope, Service, $filter, $upload) {
	
	$scope.Params = {};
	$scope.Result = false;
	$scope.Boton = true;	
	$scope.BotonLoader = false;
	$scope.Error = false;
	$scope.options={};	
	$scope.paramsSend={};	
	verify=true;
	
	$scope.init = function() {
		
		Service.loadData().then(function(dataResponse) {  
			if(dataResponse.data.error!=undefined)
	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.title=dataResponse.data.titulo;
	    		$scope.description=dataResponse.data.descri;
	    	}
		});
		
		Service.getParams().then(function(dataResponse) {  
	    	
	    	if(dataResponse.data.error!=undefined)
	    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	    	else{
	    		$scope.columns=dataResponse.data.data;
	    		//recorro los campos para cargar los data de los combos
	    		for(i=0; i<$scope.columns.length;i++){    			
	    			//si el tipo de dato es columna
	    			if($scope.columns[i].paratida=='CS' || $scope.columns[i].paratida=='CI'){    				    				    				
	    				//se pasa el codigo del combo
	    				Service.getCombo($scope.columns[i].paracomb).then(function(dataResponse) {    					    					
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
	
	
	$scope.executeProcess= function(file){
		
		$scope.trans=false;
		$scope.BotonLoader=true;
		$scope.Boton=false;		
		$scope.Error = false;
		var verify=true;	
		$scope.paramsSend={};	
		
		for(i=0; i<$scope.columns.length;i++){
			if($scope.columns[i].paratipo=='E' ){	
				//Tomar solo los datos de entrada para enviarlos a la consulta
				$scope.paramsSend[$scope.columns[i].paranomb]=$scope.Params[$scope.columns[i].paranomb];
				//Verificar si los datos requeridos cumplen con haber sido digitados
				if(!formData.$valid && ($scope.Params[$scope.columns[i].paranomb]==undefined || $scope.Params[$scope.columns[i].paranomb]=='' || ($scope.Params[$scope.columns[i].paranomb])==' ')){
					verify=false;
					break;
				}
			} 
		}	
		
		if(file==undefined || file.length<1){
			alert("Datos vacios o incorrectos: Favor adjunte el/los archivo(s) de soporte");
			verify=false;			
			$scope.BotonLoader=false;
			$scope.Boton = true;
			 
		}//validar si subieron solo 1 adjunto
           else if(file.length>1){
           alert("Solo puede adjuntar un archivo CSV");
            verify=false;                     
        }        
		//validar si subieron solo 1 adjunto tipo pdf
           else if(file.length==1){      	   
            angular.forEach(file, function(reg) {
            	
                      if(!(reg.type=="application/vnd.ms-excel")){
                    	  alert("Solo puede adjuntar un archivo CSV");
                               verify=false;
                               $scope.BotonLoader=false;
                   			   $scope.Boton = true;
                      }
            });

       }
		
		 if(verify){ 		
			
			formData=new FormData();
			for(i=0;i<file.length;i++){
				formData.append("file", file[i]);
			}
			
			formData.append("params", angular.toJson($scope.paramsSend));
			
			Service.executeProcess(formData).then(function(dataResponse) { 
				if(dataResponse.data.SUCCESS==true || dataResponse.data.SUCCESS=="true"){
        			$scope.trans=true;
        			alert('Proceso Terminado Satisfactoriamente');
        			
        			$scope.transaccion='<b>Transacci&oacute;n:</b>'+dataResponse.data.TRANSACCION;
        			if (dataResponse.data.EROR!=undefined)
        				$scope.transaccion=$scope.transaccion + '<br>' + dataResponse.data.EROR;
        		}
        		else{
        			$scope.Error = true;
        			alert('Proceso no termino Satisfactoriamente');
        			if(dataResponse.data.EROR=="")
        				$scope.DescripcionError = "Este proceso no genero ningun cambio";
        			else
        				$scope.DescripcionError = dataResponse.data.EROR;
        		
        			if(dataResponse.data.error!=undefined)
        				$scope.DescripcionError = dataResponse.data.tituloError+': '+dataResponse.data.error; 
				}
	        	
				$scope.BotonLoader=false;
				$scope.Boton = true;	
		
			});
	    }
	}
					
 }            
])