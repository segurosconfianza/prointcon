var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('FrmLogController', ['$scope', '$modal', 'FrmTransaccionService',function($scope, $modal,FrmTransaccionService) {
	
	$scope.showModal = false;
	
	$scope.$on('handleBroadcastLog', function() {
		
		$scope.trancons = FrmTransaccionService.trancons;
		
        FrmTransaccionService.getDataLog(50, 1, FrmTransaccionService.id).then(function(dataResponse) {   	        	
	        if(dataResponse.data.error!=undefined){
	        	alert(dataResponse.data.tituloError+': '+dataResponse.data.error);	       
        	}
        	else{ 
        		if(dataResponse.data.data!=null){
        			$scope.setPagingData(dataResponse.data.data,50,1);
        			$scope.totalServerItems = dataResponse.data.count;
        		}
        	}
        });	        
    }); 
	
	$scope.$on('handleBroadcastLogI18n', function() {
		
		loadI18n();	 
    }); 
    	
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
    	
    	id=0;
    	
        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                
                FrmTransaccionService.getData(pageSize, page, id).then(function(dataResponse) {
                	if(dataResponse.data.error!=undefined){
                		alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
                	}
                	else{ 
        	            data = dataResponse.data.data.filter(function(item) {
                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                        });
                        $scope.setPagingData(data,page,pageSize);
                        $scope.totalServerItems = dataResponse.data.count;
                	}
    	        });   
            } else {
            	FrmTransaccionService.getData(pageSize, page, id).then(function(dataResponse) {   
            		if(dataResponse.data.error!=undefined){
            			alert(dataResponse.data.tituloError+': '+dataResponse.data.error);
            		}
                	else{ 
                		if(dataResponse.data.data!=null){
                			$scope.setPagingData(dataResponse.data.data,page,pageSize);
                			$scope.totalServerItems = dataResponse.data.count;
                		}
                	}
    	        });    
            }
        }, 100);
    };
	
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	
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
	        
          
    function getName(i18n,colum,modulo){
    	var log = [];
    	angular.forEach(i18n, function(fila, index) {
    		if(fila.etincamp==colum && fila.modunomb==modulo)  
    			this.push(fila);
   		}, log);
    	
    	return log[0].etinetiq;        	
    }
    
    function loadI18n() {        	                                        	
    	
    	columns=[ { field: "slogcons", displayName: getName(FrmTransaccionService.getI18n(), "slogcons", "FRM_LOG"), visible: false },
                  { field: "slogtran", displayName: getName(FrmTransaccionService.getI18n(), "slogtran", "FRM_LOG"), visible: false }, 
                  { field: "slogtabl", displayName: getName(FrmTransaccionService.getI18n(), "slogtabl", "FRM_LOG")}, 
                  { field: "slogacci", displayName: getName(FrmTransaccionService.getI18n(), "slogacci", "FRM_LOG")},
                  { field: "slogregi", displayName: getName(FrmTransaccionService.getI18n(), "slogregi", "FRM_LOG")},
             ];
        $scope.columnDefs=columns;
        $scope.ventanaTitulo=getName(FrmTransaccionService.getI18n(), "-", "FRM_LOG");
    }
    
    $scope.whatClassIsIt= function(column){
    	var log = [];
    	
    	angular.forEach($scope.columnDefs, function(fila, index) {
    		if(fila.field==column)
    			this.push(fila);
   		}, log);
    	
    	return log[0].displayName;
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
            afterSelectionChange: function (rowItem, event) {                	
            	$scope.slogcons=rowItem.entity.slogcons;
            	$scope.slogtran=rowItem.entity.slogtran;
	        	$scope.slogtabl=rowItem.entity.slogtabl;
	        	$scope.slogacci=rowItem.entity.slogacci;
	        	$scope.slogregi=rowItem.entity.slogregi;               
            }
    };                              	                     
	
    $scope.fixGridRendering = function() {
    	$(window).resize();
    };

    }            
    ])