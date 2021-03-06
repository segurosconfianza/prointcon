var FrmMainApp=angular.module('FrmMainApp');

FrmMainApp.controller('SopMotivoController', ['$scope', '$modal', 'FrmTransaccionService',function($scope, $modal,FrmTransaccionService) {
	
	$scope.showModal = false;
	$scope.urlDownload=WEB_SERVER+'SopAdjunto/downloadFile?';
	
	$scope.$on('handleBroadcastSoporte', function() {
		
		$scope.trancons = FrmTransaccionService.trancons;
		
        FrmTransaccionService.getDataSoporte(50, 1, FrmTransaccionService.id).then(function(dataResponse) {   	        	
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
	
	$scope.$on('handleBroadcastSoporteI18n', function() {
		
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
    	
    	columns=[ { field: "moticons", displayName: getName(FrmTransaccionService.getI18n(), "moticons", "SOP_MOTIVO"), visible: false },
                  { field: "motitran", displayName: getName(FrmTransaccionService.getI18n(), "motitran", "SOP_MOTIVO"), visible: false }, 
                  { field: "motidesc", displayName: getName(FrmTransaccionService.getI18n(), "motidesc", "SOP_MOTIVO")},
                  { field: "adjucodi", displayName: getName(FrmTransaccionService.getI18n(), "adjucodi", "SOP_ADJUNTO"), visible: false },
                  { field: "adjuarch", displayName: getName(FrmTransaccionService.getI18n(), "adjuarch", "SOP_ADJUNTO"), visible: false },
                  { field: "adjunomb", displayName: getName(FrmTransaccionService.getI18n(), "adjunomb", "SOP_ADJUNTO")}, 
                  { field: "adjuuser", displayName: getName(FrmTransaccionService.getI18n(), "adjuuser", "SOP_ADJUNTO")},
                  { field: "adjufech", displayName: getName(FrmTransaccionService.getI18n(), "adjufech", "SOP_ADJUNTO")},
                  { field: "adjuesta", displayName: getName(FrmTransaccionService.getI18n(), "adjuesta", "SOP_ADJUNTO"), visible: false },
                  { field: "adjumoti", displayName: getName(FrmTransaccionService.getI18n(), "adjumoti", "SOP_ADJUNTO"), visible: false }
             ];
        $scope.columnDefs=columns;
        $scope.ventanaTitulo=getName(FrmTransaccionService.getI18n(), "-", "SOP_MOTIVO");
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
            	$scope.moticons=rowItem.entity.moticons;
	        	$scope.motitran=rowItem.entity.motitran;
	        	$scope.motidesc=rowItem.entity.motidesc;
	        	$scope.adjucodi=rowItem.entity.adjucodi;               
	        	$scope.adjuarch=rowItem.entity.adjuarch;
	        	$scope.adjunomb=rowItem.entity.adjunomb;
	        	$scope.adjuuser=rowItem.entity.adjuuser;
	        	$scope.adjufech=rowItem.entity.adjufech;
	        	$scope.adjuesta=rowItem.entity.adjuesta;
	        	$scope.adjumoti=rowItem.entity.adjumoti;
            }
    };                              	                     
	
    $scope.fixGridRendering = function() {
    	$(window).resize();
    };
  
    }            
    ])