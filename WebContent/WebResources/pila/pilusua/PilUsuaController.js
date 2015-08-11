var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('PilUsuaController', ['$scope', 'PilUsuaService',function($scope, Service) {
    	//botones de los formularios modal
    	$scope.buttonNew=false;
    	$scope.buttonEdit=false;
    	$scope.buttonDelete=false;    	    	                          
              
        function getName(i18n,colum){
        	var log = [];
        	angular.forEach(i18n, function(fila, index) {
        		if(fila.etincamp==colum)  
        			this.push(fila);
       		}, log);
        	
        	if(log[0]!=null)
        		return log[0].etinetiq;
        	else 
        		return "";
        }
        
        $scope.whatClassIsIt= function(column){
        	var log = [];
        	
        	angular.forEach($scope.columnDefs, function(fila, index) {
        		if(fila.field==column)
        			this.push(fila);
       		}, log);
        	
        	if(log[0]!=null)
        		return log[0].displayName;
        	else 
        		return "";
        }                
               
       $scope.createRecordForm= function(){
    	    $scope.buttonNew=true;
			$scope.buttonEdit=false;
			$scope.buttonDelete=false;
			
			$scope.usuacons = "" ,
			 $scope.usuaunit = "" ,
			 $scope.usuadive = "" ,
			 $scope.usuatiin = "" ,
			 $scope.usuarazo = "" ,
			 $scope.usuanomb = "" ,
			 $scope.usuaapel = "" ,
			 $scope.usuaemai = "" ,
			 $scope.usuatele = "" ,
			 $scope.usuapeco = "" ,
			 $scope.usuausua = "" ,
			 $scope.usuapassnu = "" ,
			 $scope.usuatipo = 1 ,
			 $scope.usuasucu = "" ,
			 $scope.usuaesta = 'A'         	
        }                
        
		$scope.loadDatatoForm= function(){			
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=true;
				$scope.buttonDelete=false;		
				
				$scope.usuapass = "";
			}
			else{
				$scope.sendAlert("Favor seleccione una fila");
				$('#myModalNew').modal('hide');
			}
        }                       
		
		$scope.deleteRecordForm= function(){
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=false;
				$scope.buttonDelete=true;					        	
			}
			else{
				$scope.sendAlert("Favor seleccione una fila");
				$('#myModalNew').modal('hide');
			}
        }
		
		$scope.validateData= function(){			
			
			if(!$scope.formData.$valid)
				return false;
			else if($scope.usuaunit==undefined || $scope.usuaunit.trim()=='')
				return false;
			else if($scope.usuatiin==undefined || $scope.usuatiin.trim()=='')
				return false;
			else if($scope.usuarazo==undefined || $scope.usuarazo.trim()=='')
				return false;
			else if($scope.usuanomb==undefined || $scope.usuanomb.trim()=='')
				return false;
			else if($scope.usuaapel==undefined || $scope.usuaapel.trim()=='')
				return false;
			else if($scope.usuaemai==undefined || $scope.usuaemai.trim()=='')
				return false;
			else if($scope.usuatele==undefined || $scope.usuatele.trim()=='')
				return false;
			else if($scope.usuapeco==undefined || $scope.usuapeco.trim()=='')
				return false;
			else if($scope.usuausua==undefined || $scope.usuausua.trim()=='')
				return false;
			else if($scope.usuatipo==undefined)
				return false;
			else if($scope.usuasucu==undefined)
				return false;
			else if($scope.usuaesta==undefined || $scope.usuaesta.trim()=='')
				return false;
			
			return true;
		}
		
		$scope.insertRecord= function(){						
			if($scope.validateData())			
				Service.insertRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
					if(dataResponse.data.error!=undefined)
						$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
			    	else{	
						 row=dataResponse.data;
						
						 $scope.usuacons = row.usuacons;
						 $scope.usuaunit = row.usuaunit;
						 $scope.usuadive = row.usuadive;
						 $scope.usuatiin = row.usuatiin;
						 $scope.usuarazo = row.usuarazo;
						 $scope.usuanomb = row.usuanomb;
						 $scope.usuaapel = row.usuaapel;
						 $scope.usuaemai = row.usuaemai;
						 $scope.usuatele = row.usuatele;
						 $scope.usuapeco = row.usuapeco;
						 $scope.usuausua = row.usuausua;
						 $scope.usuapass = row.usuapass;
						 $scope.usuatipo = row.usuatipo;
						 $scope.usuasucu = row.usuasucu;
						 $scope.usuaesta = row.usuaesta;     
							
						 $scope.sendAlert("Se creo el registro correctamente");
						 $('#myModalNew').modal('hide');
						 $scope.loadMyGrid();
			    	}
		        }); 
			else
				$scope.sendAlert("Faltan datos por diligenciar");
        }
		
		$scope.updateRecord= function(){
			if($scope.validateData())		
				Service.updateRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
					if(dataResponse.data.error!=undefined)
						$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
			    	else{	
						 row=dataResponse.data;
						
						 $scope.usuacons = row.usuacons ,
						 $scope.usuaunit = row.usuaunit ,
						 $scope.usuadive = row.usuadive ,
						 $scope.usuatiin = row.usuatiin ,
						 $scope.usuarazo = row.usuarazo ,
						 $scope.usuanomb = row.usuanomb ,
						 $scope.usuaapel = row.usuaapel ,
						 $scope.usuaemai = row.usuaemai ,
						 $scope.usuatele = row.usuatele ,
						 $scope.usuapeco = row.usuapeco ,
						 $scope.usuausua = row.usuausua ,
						 $scope.usuapass = row.usuapass ,
						 $scope.usuatipo = row.usuatipo ,
						 $scope.usuasucu = row.usuasucu ,
						 $scope.usuaesta = row.usuaesta 
											        
						 $scope.sendAlert("Se actualizo el registro correctamente");
						 $('#myModalNew').modal('hide');
						 $scope.loadMyGrid();
			    	}
		        }); 
			else
				$scope.sendAlert("Faltan datos por diligenciar");
        }
		
		$scope.deleteRecord= function(){
			$scope.usuaesta = "I"; 
			
			Service.deleteRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
				if(dataResponse.data.error!=undefined)
					$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{	
					row=dataResponse.data;
					
					$scope.usuacons = row.usuacons ;
					$scope.usuaunit = row.usuaunit ;
					$scope.usuadive = row.usuadive ;
					$scope.usuatiin = row.usuatiin ;
					$scope.usuarazo = row.usuarazo ;
					$scope.usuanomb = row.usuanomb ;
					$scope.usuaapel = row.usuaapel ;
					$scope.usuaemai = row.usuaemai ;
					$scope.usuatele = row.usuatele ;
					$scope.usuapeco = row.usuapeco ;
					$scope.usuausua = row.usuausua ;
					$scope.usuapass = row.usuapass ;
					$scope.usuatipo = row.usuatipo ;
					$scope.usuasucu = row.usuasucu ;
					$scope.usuaesta = row.usuaesta ;
	
					$scope.sendAlert("Se inactivo el registro correctamente");
					$('#myModalNew').modal('hide');
					$scope.loadMyGrid();
		    	}
	        }); 
        }	
		
		//funcion del inicializar componentes
		$scope.init = function() {
			
			Service.getI18n().then(function(dataResponse) {        	                                        	
	        	 
	        	columns=[ 	{ field: "usuacons", displayName: getName(dataResponse.data, "usuacons"), visible: false, headerCellTemplate: filterBetweenNumber } ,
							{ field: "usuaunit", displayName: getName(dataResponse.data, "usuaunit"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuadive", displayName: getName(dataResponse.data, "usuadive"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuatiin", displayName: getName(dataResponse.data, "usuatiin"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuarazo", displayName: getName(dataResponse.data, "usuarazo"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuanomb", displayName: getName(dataResponse.data, "usuanomb"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuaapel", displayName: getName(dataResponse.data, "usuaapel"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuaemai", displayName: getName(dataResponse.data, "usuaemai"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuatele", displayName: getName(dataResponse.data, "usuatele"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuapeco", displayName: getName(dataResponse.data, "usuapeco"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuausua", displayName: getName(dataResponse.data, "usuausua"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuapass", displayName: getName(dataResponse.data, "usuapass"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuatipo", displayName: getName(dataResponse.data, "usuatipo"), visible: false, headerCellTemplate: filterText } ,
							{ field: "usuasucu", displayName: getName(dataResponse.data, "usuasucu"), visible: true, headerCellTemplate: filterText } ,
							{ field: "usuaesta", displayName: getName(dataResponse.data, "usuaesta"), visible: false, headerCellTemplate: filterText }  
	                 ];
	            
	            $scope.columnDefs=columns;
	            
	            $scope.ventanaTitulo=getName(dataResponse.data, "-");
	            
	            $scope.gridOptions = {  
	                	sortInfo:{ fields: ['usuacons'], directions: ['desc']},
	                	selectedItems: [],
	                    afterSelectionChange: function (rowItem, event) {
	                    	 $scope.usuacons= rowItem.entity.usuacons,
		                   	 $scope.usuaunit= rowItem.entity.usuaunit,
		                   	 $scope.usuadive= rowItem.entity.usuadive,
		                   	 $scope.usuatiin= rowItem.entity.usuatiin,
		                   	 $scope.usuarazo= rowItem.entity.usuarazo,
		                   	 $scope.usuanomb= rowItem.entity.usuanomb,
		                   	 $scope.usuaapel= rowItem.entity.usuaapel,
		                   	 $scope.usuaemai= rowItem.entity.usuaemai,
		                   	 $scope.usuatele= rowItem.entity.usuatele,
		                   	 $scope.usuapeco= rowItem.entity.usuapeco,
		                   	 $scope.usuausua= rowItem.entity.usuausua,
		                   	 $scope.usuapass= rowItem.entity.usuapass,
		                   	 $scope.usuatipo= rowItem.entity.usuatipo,
		                   	 $scope.usuasucu= rowItem.entity.usuasucu,
		                   	 $scope.usuaesta= rowItem.entity.usuaesta       
	                    }
	                };
	            
	            $scope.basicSearchQuery=[{campo: 'usuatipo', tipo: "=", val1: 1, tipodato: "Number"} ];
	            
	            $scope.directiveGrid=true;
	        });	        			
			
			Service.getCombo("usuatiin").then(function(dataResponse) {  
				
				if(dataResponse.data.error!=undefined)
					$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{
					$scope.optionsUsuatiin = dataResponse.data;
					$scope.usuatiin = $scope.optionsUsuatiin[1];
				}
	        }); 
			
			Service.getCombo("usuaesta").then(function(dataResponse) {  
				if(dataResponse.data.error!=undefined)
					$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{				
					$scope.optionsUsuaesta = dataResponse.data;
					$scope.usuatiin = $scope.optionsUsuaesta[1];
				}
	        });

			Service.getSucursales(30).then(function(dataResponse) {    					    					
				if(dataResponse.data.error!=undefined)
					$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{    			   
		    		$scope.optionsSucursales = dataResponse.data.data;   
		    		$scope.usuaesta = $scope.optionsSucursales[1];
		    	}
			});
		}
		
		$scope.$on('gridEvento', function(event, pageSize, currentPage, order, searchQuery) {   
			$scope.pageSize=pageSize;
			$scope.currentPage=currentPage;
			$scope.order=order;
			$scope.searchQuery=searchQuery;
			if($scope.searchQuery==undefined)
				$scope.searchQuery=[];
			
	    	if($scope.directiveGrid)
	    		$scope.loadMyGrid();
	    });
		
		$scope.loadMyGrid= function(){	
			
			Service.getData($scope.pageSize, $scope.currentPage, $scope.order, $scope.searchQuery.concat($scope.basicSearchQuery)).then(function(dataResponse) {
	    		if(dataResponse.data.error!=undefined)
	    			$scope.sendAlert(dataResponse.data.tituloError+': '+dataResponse.data.error);
	        	else 
	        		$scope.$broadcast('loadDataGrid',dataResponse.data.data, dataResponse.data.count, $scope.pageSize, $scope.currentPage);
	        });
		}
		
		$scope.sendAlert = function(error){
			$scope.$broadcast('loadDataError', error);
		}
    }            
    ])
