var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('FrmPerfmoduController', ['$scope', '$modal', 'FrmPerfilService',function($scope, $modal,FrmPerfilService) {
    	//botones del formulario modal
    	$scope.buttonNewChildren=false;
    	$scope.buttonEditChildren=false;
    	$scope.buttonDeleteChildren=false;    		    
    	
    	$scope.$on('handleBroadcast', function() {
    		
    		$scope.pefinomb = FrmPerfilService.pefinomb;
    		
	        FrmPerfilService.getDataChildren(50, 1, FrmPerfilService.id).then(function(dataResponse) {   
	        	//carga el combo
		        getComboMoro();
		        if(dataResponse.data.error!=undefined){
		        	alert(dataResponse.data.tituloError+': '+dataResponse.data.error);	       
	        	}
            	else{ 
	        		if(dataResponse.data.data!=null){
	        			$scope.setPagingDataChildren(dataResponse.data.data,50,1);
	        			$scope.totalServerItemsChildren = dataResponse.data.count;
	        		}
            	}
	        });	        
	    }); 
    	
    	$scope.filterOptionsChildren = {
            filterText: "",
            useExternalFilter: true
        };
        
        $scope.totalServerItemsChildren = 0;
        
        $scope.pagingOptionsChildren = {
            pageSizes: [5, 10, 20, 50],
            pageSize: 50,
            currentPage: 1
        };  
        
        $scope.setPagingDataChildren = function(data, page, pageSize){	            
            $scope.myDataChildren = data;
            $scope.totalServerItemsChildren = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };
        
        $scope.getPagedDataAsyncChildren = function (pageSize, page, searchText) {
        	
        	id=0;
        	
            setTimeout(function () {
                var data;
                if (searchText) {
                    var ft = searchText.toLowerCase();
                    
                    FrmPerfilService.getDataChildren(pageSize, page, id).then(function(dataResponse) {
                    	if(dataResponse.data.error!=undefined){
                    		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                    	}
                    	else{ 
	        	            data = dataResponse.data.data.filter(function(item) {
	                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
	                        });
	                        $scope.setPagingDataChildren(data,page,pageSize);
	                        $scope.totalServerItemsChildren = dataResponse.data.count;
                    	}
        	        });   
                } else {
                	FrmPerfilService.getDataChildren(pageSize, page, id).then(function(dataResponse) {   
                		if(dataResponse.data.error!=undefined){
                			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                		}
                    	else{ 
	                		if(dataResponse.data.data!=null){
	                			$scope.setPagingDataChildren(dataResponse.data.data,page,pageSize);
	                			$scope.totalServerItemsChildren = dataResponse.data.count;
	                		}
                    	}
        	        });    
                }
            }, 100);
        };
    	
        $scope.getPagedDataAsyncChildren($scope.pagingOptionsChildren.pageSize, $scope.pagingOptionsChildren.currentPage);
    	
        $scope.$watch('pagingOptionsChildren', function (newVal, oldVal) {
            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
              $scope.getPagedDataAsyncChildren($scope.pagingOptionsChildren.pageSize, $scope.pagingOptionsChildren.currentPage, $scope.filterOptionsChildren.filterText);
            }
        }, true);
        
        $scope.$watch('filterOptionsChildren', function (newVal, oldVal) {
            if (newVal !== oldVal) {
              $scope.getPagedDataAsyncChildren($scope.pagingOptionsChildren.pageSize, $scope.pagingOptionsChildren.currentPage, $scope.filterOptionsChildren.filterText);
            }
        }, true);
    	        
              
        function getNameChildren(i18n,colum){
        	var log = [];
        	angular.forEach(i18n, function(fila, index) {
        		if(fila.etincamp==colum)  
        			this.push(fila);
       		}, log);
        	
        	return log[0].etinetiq;
        				         
        }
        
        FrmPerfilService.getI18n().then(function(dataResponse) {        	                                        	
        	
        	columns=[ { field: "pemocons", displayName: getNameChildren(dataResponse.data, "pemocons"), visible: false },
                      { field: "pemopefi", displayName: getNameChildren(dataResponse.data, "pemopefi"), visible: false }, 
                      { field: "pemomoro", displayName: getNameChildren(dataResponse.data, "pemomoro"), visible: false }, 
                      { field: "morocons", displayName: getNameChildren(dataResponse.data, "morocons"), visible: false },
                      { field: "moromodu", displayName: getNameChildren(dataResponse.data, "moromodu"), visible: false },
                      { field: "mororope", displayName: getNameChildren(dataResponse.data, "mororope"), visible: false },
                      { field: "moducons", displayName: getNameChildren(dataResponse.data, "moducons"), visible: false },
                      { field: "moduapli", displayName: getNameChildren(dataResponse.data, "moduapli"), visible: false },
                      { field: "modunomb", displayName: getNameChildren(dataResponse.data, "modunomb"), width: 150 },
                      { field: "modunemo", displayName: getNameChildren(dataResponse.data, "modunemo"), visible: false },                      
                      { field: "ropecons", displayName: getNameChildren(dataResponse.data, "ropecons"), visible: false },
                      { field: "ropenomb", displayName: getNameChildren(dataResponse.data, "ropenomb"), width: 50 },
                      { field: "ropedesc", displayName: getNameChildren(dataResponse.data, "ropedesc"), visible: false },
                      { field: "ropetipo", displayName: getNameChildren(dataResponse.data, "ropetipo"), visible: false  },
                      { field: "aplicons", displayName: getNameChildren(dataResponse.data, "aplicons"), visible: false },
                      { field: "aplinomb", displayName: getNameChildren(dataResponse.data, "aplinomb"), width: 100 },
                      { field: "aplidesc", displayName: getNameChildren(dataResponse.data, "aplidesc"), visible: false },
                      { field: "apliesta", displayName: getNameChildren(dataResponse.data, "apliesta"), visible: false },
                      { field: "aplifecr", displayName: getNameChildren(dataResponse.data, "aplifecr"), visible: false },                      
                      { field: "tablvast", displayName: getNameChildren(dataResponse.data, "ropetipo"), width: 70 },
                      { field: "modudurl", displayName: getNameChildren(dataResponse.data, "modudurl") }
                 ];
            
            $scope.columnDefsChildren=columns;
            
            $scope.ventanaTituloChildren="Permisos";
        });
        
        $scope.whatClassIsItChildren= function(column){
        	var log = [];
        	
        	angular.forEach($scope.columnDefsChildren, function(fila, index) {
        		if(fila.field==column)
        			this.push(fila);
       		}, log);
        	
        	return log[0].displayName;
        }
        
        $scope.gridOptionsChildren = {
                data: 'myDataChildren',
                enablePaging: true,
                showFooter: true,
                totalServerItems:'totalServerItemsChildren',
                pagingOptions: $scope.pagingOptionsChildren,
                filterOptions: $scope.filterOptionsChildren,
                multiSelect: false,
                columnDefs:'columnDefsChildren',
                selectedItems: [],
                showColumnMenu: true,
                enableColumnResize: true
        };
        
       $scope.createRecordFormChildren= function(){
    	   
    	   console.log(FrmPerfilService.id);
    	   
    	   if( FrmPerfilService.id!=null && FrmPerfilService.id!=0){
	    	    $scope.buttonNewChildren=true;
				$scope.buttonEditChildren=false;
				$scope.buttonDeleteChildren=false;
				
	        	$scope.pemocons="";
	        	$scope.pemopefi="";
	        	$scope.pemomoro="";
    		}
    	   else
    		   alert("Favor seleccione un Perfil");
        }                
        
		$scope.loadDatatoFormChildren= function(){
			
			if(FrmPerfilService.id!=null && FrmPerfilService.id!=0){
				$scope.buttonNewChildren=false;
				$scope.buttonEditChildren=true;
				$scope.buttonDeleteChildren=false;
				
				if($scope.gridOptionsChildren.selectedItems.length>0){
		        	var row=$scope.gridOptionsChildren.selectedItems[0];
		        	$scope.pemocons=row.pemocons;
		        	$scope.pemopefi=row.pemopefi;
		        	$scope.pemomoro=row.pemomoro;
				}
				else
					alert("Favor seleccione una fila");
			}
			else
	    		alert("Favor seleccione un Perfil");
        }                       
		
		$scope.deleteRecordFormChildren= function(){

			if(FrmPerfilService.id!=null && FrmPerfilService.id!=0){
				$scope.buttonNewChildren=false;
				$scope.buttonEditChildren=false;
				$scope.buttonDeleteChildren=true;
				
				if($scope.gridOptionsChildren.selectedItems.length>0){
		        	var row=$scope.gridOptionsChildren.selectedItems[0];
		        	
		        	$scope.pemocons=row.pemocons;
		        	$scope.pemopefi=row.pemopefi;
		        	$scope.pemomoro=row.pemomoro;
				}
				else
					alert("Favor seleccione una fila");
			}
			else
	    		alert("Favor seleccione un Perfil");
        }
		
		$scope.insertRecordChildren= function(){						
			
			FrmPerfilService.insertRecordChildren($scope.pemocons, FrmPerfilService.id, $scope.pemomoro).then(function(dataResponse) {        	            
				row=dataResponse.data;
				$scope.pemocons=row.pemocons;
	        	$scope.pemopefi=row.pemopefi;
	        	$scope.pemomoro=row.pemomoro;
	        	
	        	FrmPerfilService.loadChildren();
	        }); 
        }
		
		$scope.updateRecordChildren= function(){
			
			console.log("$scope.updateRecordChildren");
			
			FrmPerfilService.updateRecordChildren($scope.pemocons, FrmPerfilService.id, $scope.pemomoro).then(function(dataResponse) {        	            
				row=dataResponse.data;
				$scope.pemocons=row.pemocons;
	        	$scope.pemopefi=row.pemopefi;
	        	$scope.pemomoro=row.pemomoro;
	        	
	        	FrmPerfilService.loadChildren();
	        }); 
        }
		
		$scope.deleteRecordChildren= function(){
			
			FrmPerfilService.deleteRecordChildren($scope.pemocons, FrmPerfilService.id, $scope.pemomoro).then(function(dataResponse) {        	            
				row=dataResponse.data;
				$scope.pemocons=row.pemocons;
	        	$scope.pemopefi=row.pemopefi;
	        	$scope.pemomoro=row.pemomoro;
	        	
	        	FrmPerfilService.loadChildren();				
	        }); 
        }
		
		//funcion del combo
		function getComboMoro() {	
			
			if($scope.optionsMoro == null){
				FrmPerfilService.getComboMoro().then(function(dataResponse) {  
					
					if(dataResponse.data.error==undefined){
						$scope.optionsMoro = dataResponse.data;
						$scope.pemomoro = $scope.optionsMoro[1];
					}
		        }); 
				
			}
		}
				
    }            
    ])