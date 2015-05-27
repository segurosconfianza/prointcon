var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('PilUsuaController', ['$scope', 'PilUsuaService',function($scope, PilUsuaService) {
    	//botones de los formularios modal
    	$scope.buttonNew=false;
    	$scope.buttonEdit=false;
    	$scope.buttonDelete=false;    	
    	
        $scope.filterOptions = {
            filterText: "",
            useExternalFilter: true
        };
        
        $scope.totalServerItems = 0;
        
        $scope.pagingOptions = {
            pageSizes: [5, 10, 20, 50],
            pageSize: 50,
            currentPage: 1
        };  
        
        $scope.setPagingData = function(data, page, pageSize){	            
            $scope.myData = data;
            $scope.totalServerItems = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };
        
        $scope.getPagedDataAsync = function (pageSize, page, searchText) {
            setTimeout(function () {
                var data;
                if (searchText) {
                    var ft = searchText.toLowerCase();
                    
                    PilUsuaService.getData(pageSize, page).then(function(dataResponse) {  
                    	
                    	if(dataResponse.data.error!=undefined)
                    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                    	else{                    	
	        	            data = dataResponse.data.data.filter(function(item) {
	                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
	                        });
	                        $scope.setPagingData(data,page,pageSize);
	                        $scope.totalServerItems = dataResponse.data.count;
                    	}
        	        });   
                } else {
                	PilUsuaService.getData(pageSize, page).then(function(dataResponse) {
                		if(dataResponse.data.error!=undefined)
                			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                    	else{ 
                    		$scope.setPagingData(dataResponse.data.data,page,pageSize);
                    		$scope.totalServerItems = dataResponse.data.count;
                    	}
        	        });    
                }
            }, 100);
        };    	       
    	
        $scope.$watch('pagingOptions', function (newVal, oldVal) {
            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);
        
        $scope.$watch('filterOptions', function (newVal, oldVal) {
            if (newVal !== oldVal) {
              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
            }
        }, true);
    	        
              
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
        
        $scope.gridOptions = {
                data: 'myData',
                enablePaging: true,
                showFooter: true,
                totalServerItems:'totalServerItems',
                pagingOptions: $scope.pagingOptions,
                filterOptions: $scope.filterOptions,
                multiSelect: false,
                columnDefs:'columnDefs',
                selectedItems: [],                
                showColumnMenu: true,
                enableColumnResize: true,
                showFilter : true,
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
			 $scope.usuapass = "" ,
			 $scope.usuatipo = 2 ,
			 $scope.usuasucu = "" ,
			 $scope.usuaesta = A         	
        }                
        
		$scope.loadDatatoForm= function(){			
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=true;
				$scope.buttonDelete=false;					        	
			}
			else
				alert("Favor seleccione una fila");
        }                       
		
		$scope.deleteRecordForm= function(){
			
			if($scope.gridOptions.selectedItems.length>0){
				$scope.buttonNew=false;
				$scope.buttonEdit=false;
				$scope.buttonDelete=true;					        	
			}
			else
				alert("Favor seleccione una fila");
        }
		
		$scope.insertRecord= function(){						
			
			PilUsuaService.insertRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
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
								
	        	alert("Se creo el registro correctamente");
				$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	        }); 
        }
		
		$scope.updateRecord= function(){
			
			PilUsuaService.updateRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
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
									        
	        	alert("Se actualizo el registro correctamente");
				
				$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	        }); 
        }
		
		$scope.deleteRecord= function(){
						
			PilUsuaService.deleteRecord($scope.usuacons,$scope.usuaunit,$scope.usuadive,$scope.usuatiin,$scope.usuarazo,$scope.usuanomb,$scope.usuaapel,$scope.usuaemai,$scope.usuatele,$scope.usuapeco,$scope.usuausua,$scope.usuapass,$scope.usuatipo,$scope.usuasucu,$scope.usuaesta).then(function(dataResponse) {        	            
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

	        	alert("Se borro el registro correctamente");
				
				$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	        }); 
        }	
		
		//funcion del inicializar componentes
		$scope.init = function() {
			
			PilUsuaService.getI18n().then(function(dataResponse) {        	                                        	
	        	 
	        	columns=[ 	{ field: "usuacons", displayName: getName(dataResponse.data, "usuacons"), visible: false } ,
							{ field: "usuaunit", displayName: getName(dataResponse.data, "usuaunit"), visible: true } ,
							{ field: "usuadive", displayName: getName(dataResponse.data, "usuadive"), visible: true } ,
							{ field: "usuatiin", displayName: getName(dataResponse.data, "usuatiin"), visible: true } ,
							{ field: "usuarazo", displayName: getName(dataResponse.data, "usuarazo"), visible: true } ,
							{ field: "usuanomb", displayName: getName(dataResponse.data, "usuanomb"), visible: true } ,
							{ field: "usuaapel", displayName: getName(dataResponse.data, "usuaapel"), visible: true } ,
							{ field: "usuaemai", displayName: getName(dataResponse.data, "usuaemai"), visible: false } ,
							{ field: "usuatele", displayName: getName(dataResponse.data, "usuatele"), visible: false } ,
							{ field: "usuapeco", displayName: getName(dataResponse.data, "usuapeco"), visible: true } ,
							{ field: "usuausua", displayName: getName(dataResponse.data, "usuausua"), visible: true } ,
							{ field: "usuapass", displayName: getName(dataResponse.data, "usuapass"), visible: false } ,
							{ field: "usuatipo", displayName: getName(dataResponse.data, "usuatipo"), visible: false } ,
							{ field: "usuasucu", displayName: getName(dataResponse.data, "usuasucu"), visible: true } ,
							{ field: "usuaesta", displayName: getName(dataResponse.data, "usuaesta"), visible: false }  
	                 ];
	            
	            $scope.columnDefs=columns;
	            
	            $scope.ventanaTitulo=getName(dataResponse.data, "-");
	        });
	        
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
			
			PilUsuaService.getCombo("usuatiin").then(function(dataResponse) {  
				
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{
					$scope.optionsUsuatiin = dataResponse.data;
					$scope.usuatiin = $scope.optionsUsuatiin[1];
				}
	        }); 
			
			PilUsuaService.getCombo("usuaesta").then(function(dataResponse) {  
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{				
					$scope.optionsUsuaesta = dataResponse.data;
					$scope.usuatiin = $scope.optionsUsuaesta[1];
				}
	        });

			PilUsuaService.getSucursales(5).then(function(dataResponse) {    					    					
				if(dataResponse.data.error!=undefined)
		    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
		    	else{    			   
		    		$scope.optionsSucursales = dataResponse.data.data;   
		    		$scope.usuaesta = $scope.optionsSucursales[1];
		    	}
			});
		}
				
    }            
    ])
