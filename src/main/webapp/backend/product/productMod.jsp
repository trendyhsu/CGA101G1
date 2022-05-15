<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/backend/share.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gamecompany.model.*"%>
<%@ page import="com.gametype.model.*"%>
<%@ page import="com.gameplatformtype.model.*"%>



<%
GameCompanyService gameCompanyService = new GameCompanyService();
List<GameCompanyVO> gameCompanyList = gameCompanyService.getAll();
pageContext.setAttribute("gameCompanyList", gameCompanyList);

GameTypeService gameTypeService = new GameTypeService();
List<GameTypeVO> gameTypeList = gameTypeService.getAll();
pageContext.setAttribute("gameTypeList", gameTypeList);

GamePlatformTypeService gamePlatformTypeService = new GamePlatformTypeService();
List<GamePlatformTypeVO> gamePlatformTypeList = gamePlatformTypeService.getAll();
pageContext.setAttribute("gamePlatformTypeList", gamePlatformTypeList);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>修改商品內容</title>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/3.0.2/vue.global.js"></script>
<style>
.descriptionArea {
	width: 200px;
	/* white-space: pre-wrap; */
	overflow: hidden;
	text-overflow: ellipsis;
}

</style>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>



	<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper site-min-height">
			<h3>
				<i class="fa fa-angle-right"></i> 商品修改
			</h3>
			<div class="row mt">
				<div class="col-lg-12">
					<div id="app">

<!-- 						<div style="width: 30%"> -->
<!-- 							<input class="form-control" type="text" name="keysearch" id="" -->
<!-- 								@change="keysearch($event)" placeholder="搜尋遊戲名稱"> -->
<!-- 						</div> -->
						<div v-show="this.search.length !=0">
							目前總共<span style="color: blue">{{this.search.length}}</span>筆資料
						</div>
						<table class="table table-striped" style="margin-bottom: 0">
							<thead>
								<tr>
									<th style="width: 65px;padding-left: 0;padding-right: 0;padding-bottom: 0">
										<div>
											<input class="form-control" type="number" name="NoSearch"
												id="" @change="NoSearch($event)" v-model="searchNo" placeholder="產品編號" style="padding: 0;border: 0;">
										</div>
									</th>
									<th style="width: 110px;padding-left: 0;padding-right: 0;padding-bottom: 0">
										<div>
											<input class="form-control" type="text" name="keysearch"
												id="" @change="keysearch($event)" v-model="searchName" placeholder="產品名稱" style="padding: 0;border-color: white;">
										</div>
									</th>
									<th style="width: 50px;">價格</th>
									<th style="width: 80px;">封面</th>
									<th style="width: 1%">
										<div>
											<select name="GameTypeNo" id="gameTypeNo"
												@change="typesearch($event)" v-model="searchType">
												<option value="0">遊戲種類	</option>
												<c:forEach var="gameTypeVO" items="${gameTypeList}">
													<option value="${gameTypeVO.gameTypeNo}">${gameTypeVO.gameTypeName}</option>
												</c:forEach>
											</select>
										</div>
									</th>

									<th style="width: 100px;">
										<div>
											<select name="GamePlatformNo" id="gamePlatformNo"
												@change="Platformsearch($event)" v-model="searchPlatform">
												<option value="0">平台種類</option>
												<c:forEach var="gamePlatformTypeVO"
													items="${gamePlatformTypeList}">
													<option value="${gamePlatformTypeVO.gamePlatformNo}">${gamePlatformTypeVO.gamePlatformName}</option>
												</c:forEach>
											</select>
										</div>
									</th>
									<th style="width: 120px">遊戲公司</th>
									<th style="width: 1%">
										<div>
											<select name="ProductState" id="productState"
												@change="Statesearch($event)" v-model="searchState">
												<option value="2">銷售狀態</option>
												<option value="0">未上架</option>
												<option value="1">販售中</option>
											</select>
										</div>
									</th>
									<th style="width: 120px;">產品描述</th>
									<th style="width: 110px">UpcNum</th>
									<th>產品圖片2</th>
									<th>產品圖片3</th>
									<th style="text-align: center;">編輯</th>
								</tr>
							</thead>
						</table>
						<div v-if="loadingState">
							<div style="margin-left: 47%; margin-top: 20%">
								<h3>資料讀取中......</h3>
							</div>

							<div class="spinner-border text-info"
								style="margin-left: 50%; margin-top: 2%" role="status">
								<span class="visually-hidden">Loading...</span>
							</div>
						</div>
						<div v-if="((this.search.length ==0)&&(!loadingState))">
							<div style="margin-left: 47%; margin-top: 15%">
								<h3>查無資料</h3>
							</div>
						</div>

						<div v-show="this.search.length !=0">
							<card-template :read-data="renderData" :now-page="nowPage"
								v-on:emitedit="edit"> </card-template>
							<nav class="d-flex justify-content-center">
								<pagination-list :pages="renderData" :now-page="nowPage"
									:total-page="totalPage" @skip-page="skipPage"
									@previous-page="previousPage" @next-page="nextPage">
								</pagination-list>
							</nav>
						</div>
						<!--   form內容要進去資料庫  -->
						<div style="width: 50%; font-size: 1.5em;" class="container">
							<form v-if="isNew || temp.productNo" method="post"
								action="/CGA101G1/product/GetParameter"
								enctype="multipart/form-data" required>
								<!-- <form method="post" action="GetParameter" enctype="multipart/form-data"> -->
								<div class="row">
									<div class="col">
										<label for="productNo" class="form-label">產品編號</label> <input
											type="text" id="productNo" name="ProductNo"
											class="form-control" v-model="temp.productNo" readonly>
									</div>
									<div class="col">
										<label for="productName" class="form-label">產品名稱</label> <input
											type="text" id="productName" name="ProductName"
											class="form-control" v-model="temp.productName"
											@change="nameCheck($event)">
										<div style="color: red;"
											:style="{visibility: nameInput ? 'visible' : 'hidden'}">
											商品名稱: 只能包含中文、英文大小寫、數字、底線及冒號 , 且長度須在1到30之間!!</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<label for="productPrice" class="form-label">價格</label> <input
											type="number" id="productPrice" name="ProductPrice"
											class="form-control" v-model="temp.productPrice"
											@change="priceCheck($event)">
										<div style="color: red;"
											:style="{visibility: priceInput ? 'visible' : 'hidden'}">
											價格應該落在99~99,999元之間!!</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<label for="productState" class="form-label">銷售狀態</label> <input
											type="radio" id="productStateUn" name="ProductState"
											v-model="temp.productState" value="0" checked=""> <label
											for="productStateUn" class="form-label">下架</label> <input
											type="radio" id="productStateS" name="ProductState"
											v-model="temp.productState" value="1"> <label
											for="productStateS" class="form-label">上架</label>
									</div>

									<div class="col">
										<label for="gameTypeNo" class="form-label">遊戲種類</label> <select
											name="GameTypeNo" id="gameTypeNo" v-model="temp.gameTypeNo"
											required>
											<c:forEach var="gameTypeVO" items="${gameTypeList}">
												<option value="${gameTypeVO.gameTypeNo}">${gameTypeVO.gameTypeName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<label for="gamePlatformNo" class="form-label">遊戲平台種類</label>
										<select name="GamePlatformNo" id="gamePlatformNo"
											v-model="temp.gamePlatformNo" required>
											<c:forEach var="gamePlatformTypeVO"
												items="${gamePlatformTypeList}">
												<option value="${gamePlatformTypeVO.gamePlatformNo}">${gamePlatformTypeVO.gamePlatformName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col">
										<label for="gameCompanyNo" class="form-label">遊戲公司</label> <select
											name="GameCompanyNo" id="gameCompanyNo"
											v-model="temp.gameCompanyNo" required>
											<c:forEach var="gameCompanyVO" items="${gameCompanyList}">
												<option value="${gameCompanyVO.gameCompanyNo}">${gameCompanyVO.gameCompanyName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col">
									<label for="upcNum" class="form-label">UpcNum</label> <input
										type="number" id="upcNum" name="UpcNum" class="form-control"
										v-model="temp.upcNum" required @change="upcNumCheck($event)"
										maxlength="50">
									<div style="color: red;"
										:style="{visibility: upcNumInput ? 'visible' : 'hidden'}">
										upcNum不應該都是0 !!</div>
								</div>
								<div>
									<label for="itemProdDescription" class="form-label">遊戲描述</label>

								</div>
								<div>
									<textarea name="ItemProdDescription" id="itemProdDescription"
										cols="81" rows="10" @change="descriptCheck($event)" required>{{temp.itemProdDescription}}</textarea>
									<div style="color: red;"
										:style="{visibility: descriptInput ? 'visible' : 'hidden'}">
										請填入遊戲描述 !!</div>
								</div>


								<div>
									<label v-if="url2" class="form-label" hidden>封面照片編號</label> <input
										v-if="url2" type="text" name="productImage1NO"
										class="form-control" v-model="temp.picList[0].productPicNo"
										readonly hidden>
								</div>
								<div class="row">
									<div class="col">
										<label for="productImage1" class="form-label">封面照片</label> <input
											type="file" id="productImage1" name="productImage1"
											class="form-control" v-on:change="onFileChange">
									</div>
									<div class="col">
										<img v-if="url" :src="url" class="img-fluid d-block" alt=""
											width="100">
									</div>
								</div>

								<div>
									<label v-if="url2" class="form-label" hidden>產品圖片2編號</label> <input
										v-if="url2" type="text" name="productImage2NO"
										class="form-control" v-model="temp.picList[1].productPicNo"
										readonly hidden>
								</div>
								<div class="row">
									<div class="col">
										<label for="productImage2" class="form-label">產品圖片2</label> <input
											type="file" id="productImage2" name="productImage2"
											class="form-control" v-on:change="onFileChange2">
									</div>
									<div class="col">
										<img v-if="url2" :src="url2" class="img-fluid d-block" alt=""
											width="100">
									</div>
								</div>

								<div>
									<label v-if="url3" class="form-label" hidden>產品圖片3編號</label> <input
										v-if="url3" type="text" name="productImage3NO"
										class="form-control" v-model="temp.picList[2].productPicNo"
										readonly hidden>
								</div>
								<div class="row">
									<div class="col">
										<label for="productImage3" class="form-label">產品圖片3</label> <input
											type="file" id="productImage3" name="productImage3"
											class="form-control" v-on:change="onFileChange3">

									</div>
									<div class="col">
										<img v-if="url3" :src="url3" class="img-fluid d-block" alt=""
											width="100">
									</div>
								</div>

								<input type="SUBMIT" v-on:click="confirmEdit" :disabled="btn">
							</form>
						</div>
					</div>
				</div>
			</div>

		</section>

	</section>
	<!-- /MAIN CONTENT -->

	<!--main content end-->
	<!--footer start-->
	<footer class="site-footer">
		<div class="text-center">
			2014 - Alvarez.is <a href="blank.html#" class="go-top"> <i
				class="fa fa-angle-up"></i>
			</a>
		</div>
	</footer>
	<!--footer end-->
	</section>

	<!-- js placed at the end of the document so the pages load faster -->



	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!--script for this page-->

	<script>
                //custom select box

                // $(function () {
                //     $('select.styled').customSelect();
                // });
                const app = Vue.createApp({
                    data() {
                        return {
                            datastore: [],
                            loadingState:true,
                            search: [],
                            picData: [],
                            gametype: [],
                            gameplatform: [],
                            productStatus: [
                                {
                                    productStatusNo: 0,
                                    productStatusName: "未開賣"
                                },
                                {
                                    productStatusNo: 1,
                                    productStatusName: "販售中"
                                },
                            ],

                            searchName:'',
                            searchNo:'',
                            searchType:0,
                            searchPlatform:0,
                            searchState:2,



                            temp: {},
                            isNew: false,
                            url: null,
                            url2: null,
                            url3: null,
                            //驗證表單用
                            nameInput: false,
                            priceInput: false,
                            upcNumInput: false,
                            descriptInput:false,

                            btn: false,



                            renderData: [], // 建立每頁顯示資料
                            pageBaseNum: 4,  // 每頁顯示資料數量
                            nowPage: 0, // 初始、現在頁數

                        }
                    },
                    methods: {
                        renderingPage() {
                            for (let n = 0; n < this.totalPage; n++) { // 依每頁顯示數量建立新陣列
                                this.renderData.push(
                                    (this.search.slice
                                        (n * this.pageBaseNum, n * this.pageBaseNum + this.pageBaseNum)
                                    )
                                );
                            }
                            this.loadingState=false;
                        },
                        getProductData() {
                            const apiUrl = '/CGA101G1/product/OneProductToJson';
                            axios.get(apiUrl)
                                // 一定要用箭頭函示!!!!
                                .then((response) => {
                                    console.log("取ProductData的狀態：" + response.status); //狀態碼

                                    this.datastore = response.data;//塞進去
                                    console.log("datastore讀取資料ok");
                                    this.getPicData();
                                    this.getGametype();
                                    this.getGameplatform();
                                    this.insertproductStatusName(this.productStatus);
                                    for (product of this.datastore) {
                                        this.search.push(product);
                                    }



                                    this.renderingPage();
                                });
                        },
                        getPicData() {
                            const apiUrl = '/CGA101G1/product/ProductPics';
                            axios.get(apiUrl)
                                // 一定要用箭頭函示!!!!
                                .then((response) => {
                                    console.log("取pic的狀態：" + response.status); //狀態碼
                                    this.picData = response.data;//塞進去
                                    this.insertPic(this.picData);
                                });
                        },
                        insertPic(picData) {
                            console.log("insertPic開始");
                            for (j = 0; j < this.datastore.length; j++) {
                                this.datastore[j].picList = [];
                                for (i = 0; i < picData.length; i++) {
                                    if (picData[i].productNo == this.datastore[j].productNo) {
                                        this.datastore[j].picList.push(picData[i]);
                                    }
                                }
                            }
                            this.picData = [];
                            console.log("insert完畢");
                        },
                        getGametype() {
                            const apiUrl = '/CGA101G1/gametype/getAllGameType';
                            axios.get(apiUrl)
                                // 一定要用箭頭函示!!!!
                                .then((response) => {
                                    console.log("取gametype的狀態：" + response.status); //狀態碼
                                    this.gametype = response.data;//塞進去
                                    this.insertGametype(this.gametype);

                                });
                        },
                        insertGametype(gametype) {
                            console.log("insertgametype開始");
                            for (j = 0; j < this.datastore.length; j++) {
                                this.datastore[j].gameTypeName = "";
                                for (i = 0; i < gametype.length; i++) {
                                    if (gametype[i].gameTypeNo == this.datastore[j].gameTypeNo) {
                                        this.datastore[j].gameTypeName = gametype[i].gameTypeName;
                                    }
                                }
                            }
                            console.log("insert完畢");
                        },
                        getGameplatform() {
                            const apiUrl = '/CGA101G1/gameplatformtype/getAllGamePlatformType';
                            axios.get(apiUrl)
                                // 一定要用箭頭函示!!!!
                                .then((response) => {
                                    console.log("取gameplatformtype的狀態：" + response.status); //狀態碼
                                    this.gameplatform = response.data;//塞進去
                                    this.insertGameplatform(this.gameplatform);

                                });
                        },
                        insertGameplatform(gameplatform) {
                            console.log("insertgameplatform開始");
                            for (j = 0; j < this.datastore.length; j++) {
                                this.datastore[j].gamePlatformName = "";
                                for (i = 0; i < gameplatform.length; i++) {
                                    if (gameplatform[i].gamePlatformNo == this.datastore[j].gamePlatformNo) {
                                        this.datastore[j].gamePlatformName = gameplatform[i].gamePlatformName;
                                    }
                                }
                            }
                            console.log("insert完畢");
                        },
                        insertproductStatusName(productStatus) {
                            console.log("insertgameplatform開始");
                            for (j = 0; j < this.datastore.length; j++) {
                                this.datastore[j].StatusName = "";
                                for (i = 0; i < productStatus.length; i++) {
                                    if (productStatus[i].productStatusNo == this.datastore[j].productState) {
                                        this.datastore[j].StatusName = productStatus[i].productStatusName;
                                    }
                                }
                            }
                            console.log("insert完畢");
                        },
                        keysearch(e) {
                        	this.searchNo='';
                        	this.searchType=0;
                        	this.searchPlatform=0;
                        	this.searchState=2;
                        	this.loadingState=true;
                            this.searchName=e.target.value;
                            this.search = [];
                            this.datastore.filter(item => {
                            	console.log(item.productName);
                                if (item.productName.indexOf(e.target.value) !== -1) {
                                    this.search.push(item);
                                }
                            });
                            this.renderData = [];
                            this.renderingPage();

                        },
                        NoSearch(e) {
                        	this.searchName='';
                        	this.searchType=0;
                        	this.searchPlatform=0;
                        	this.searchState=2;
                        	this.loadingState=true;
                        	this.searchNo=e.target.value;
                            this.search = [];
                            this.datastore.filter(item => {
                                if (item.productNo.toString().indexOf(e.target.value) !== -1) {
                                    this.search.push(item);
                                }
                            });
                            this.renderData = [];
                            this.renderingPage();

                        },
                        typesearch(e) {
                        	this.searchName='';
                        	this.searchNo='';
                        	this.searchPlatform=0;
                        	this.searchState=2;
                        	this.loadingState=true;
                            console.log(e.target.value);
                            this.search = [];
                            this.datastore.filter(item => {
                                if (item.gameTypeNo.toString().indexOf(e.target.value) !== -1) {
                                    this.search.push(item);
                                }
                            });
                            this.renderData = [];
                            this.renderingPage();

                        },
                        Platformsearch(e) {
                        	this.searchName='';
                        	this.searchNo='';
                        	this.searchType=0;
                        	this.searchState=2;
                        	this.loadingState=true;
                            console.log(e.target.value);
                            this.search = [];
                            this.datastore.filter(item => {
                                if (item.gamePlatformNo.toString().indexOf(e.target.value) !== -1) {
                                    this.search.push(item);
                                }
                            });
                            this.renderData = [];
                            this.renderingPage();

                        },
                        Statesearch(e) {
                        	this.searchName='';
                        	this.searchNo='';
                        	this.searchType=0;
                        	this.searchPlatform=0;
                        	this.loadingState=true;
                            console.log(e.target.value);
                            console.log(e.target.value == 2);
                            this.search = [];
                            if(e.target.value == 2){
                                for (product of this.datastore) {
                                    this.search.push(product);
                                }
	                            this.renderData = [];
	                            this.renderingPage();
                            }
                            else{
	                            this.datastore.filter(item => {
	                                if (item.productState.toString().indexOf(e.target.value) !== -1) {
	                                    this.search.push(item);
	                                }
	                            });
	                            this.renderData = [];
	                            this.renderingPage();
                            }

                        },
                        edit(para) {
                            console.log("帶入想要編輯的物件")
                            this.temp = {};
                            this.temp = { ...para };//淺層拷貝，把點選編輯的物件放進temp
                            this.url = this.temp.picList[0].imageUrl;
                            this.url2 = this.temp.picList[1].imageUrl;
                            this.url3 = this.temp.picList[2].imageUrl;
                        },
                        onFileChange(e) {
                            const file = e.target.files[0];
                            this.url = URL.createObjectURL(file);
                        },
                        onFileChange2(e) {
                            const file2 = e.target.files[0];
                            this.url2 = URL.createObjectURL(file2);
                        },
                        onFileChange3(e) {
                            const file3 = e.target.files[0];
                            this.url3 = URL.createObjectURL(file3);
                        },



                        previousPage() {
                            if (this.nowPage != 0) { // 頁數初始值為 0 
                                this.nowPage = this.nowPage - 1;  // 當頁數不是初始值，減一頁(回上一頁)
                            }
                        },
                        nextPage() {
                            if (this.nowPage < this.totalPage - 1) {
                                this.nowPage++;  // 當頁數小於總頁數，加一頁(下一頁)
                            }
                        },
                        skipPage(pageNum) {
                            this.nowPage = pageNum; // 將資料換成點擊的頁碼，切換頁面
                        },

                        //驗證表單
                        nameCheck(e) {
                            this.nameInput = false;
                            const rules = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_:)(\\-\\)]{1,30}$/;
                            if (!rules.test(e.target.value) || (e.target.value).trim().length == 0) {
                                this.nameInput = true;
                                this.btn = true;
                            } else if(this.nameInput+this.priceInput+this.upcNumInput+this.descriptInput==0) {
                                this.btn = false;
                            }
                        },
                        priceCheck(e) {
                            this.priceInput = false;
                            if ((e.target.value).trim().length >= 5 || (e.target.value).trim().length <= 2) {
                                this.priceInput = true;
                                this.btn = true;
                            } else  if(this.nameInput+this.priceInput+this.upcNumInput+this.descriptInput==0){
                                this.btn = false;
                            }
                        },
                        upcNumCheck(e) {
                            this.upcNumInput = false;
                            const rules = /^[0]{1,50}$/;
                            if (rules.test(e.target.value) || (e.target.value).trim().length == 0) {
                                this.upcNumInput = true;
                                this.btn = true;
                            } else  if(this.nameInput+this.priceInput+this.upcNumInput+this.descriptInput==0){
                                this.btn = false;
                            }
                        },
                        descriptCheck(e) {
                            this.descriptInput = false;
                            if ((e.target.value).trim().length ==0) {
                                this.descriptInput = true;
                                this.btn = true;
                            } else  if(this.nameInput+this.priceInput+this.upcNumInput+this.descriptInput==0){
                                this.btn = false;
                            }
                        },


                    },
                    computed: {
                        totalPage() {
                            return Math.ceil(this.search.length / this.pageBaseNum); // 依每頁數量計算總頁數
                        }
                    },
                    created() {
                        this.getProductData();

                    }
                })

                app.component('card-template', {
                    data() {
                        return {

                        }
                    },
                    props: ['readData', 'nowPage'],
                    methods: {
                        webcamSendRequestButton(para) {
                            console.log(para);
                            console.log({ ...para });
                            this.$emit('emitedit', para);
                        },
                    },
                    template: `  <table class="table table-striped">
                                <tbody>
                                    <tr v-for="item in readData[nowPage]" :key="item.productNo"
                                        v-bind:class="{'table-success':item.onStock}">
                                        <td style="width: 62px;padding-left: 0;padding-right: 0;">{{item.productNo}}</td>
                                        <td style="width: 110px">{{item.productName}}</td>
                                        <td style="width: 50px">{{item.productPrice}}</td>
                                        <td style="width: 20px">
                                            <img :src="item.picList[0].imageUrl" alt="" width="66">
                                        </td>
                                        <td style="width: 120px">{{item.gameTypeName}}</td>
                                        <td style="width: 100px">{{item.gamePlatformName}}</td>
                                        <td style="width: 120px">{{item.gameCompanyName}}</td>
                                        <td style="width: 80px">{{item.StatusName}}</td>
                                        <td class="descriptionArea" style="width: 120px;">
                                        <div style="width: 100px; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 4;-webkit-box-orient: vertical;">{{item.itemProdDescription}}</div>
                                        </td>
                                        <td>{{item.upcNum}}</td>
                                        <td>
                                            <img :src="item.picList[1].imageUrl" alt="" width="120">
                                        </td>
                                        <td>
                                            <img :src="item.picList[2].imageUrl" alt="" width="120">
                                        </td>
                                        <td><button type="button" class="btn btn-secondary"
                                                @click="webcamSendRequestButton(item)">編輯</button></td>
                                    </tr>
                                </tbody>
                         </table>
  `,
                })

                app.component('pagination-list', {
                    props: ['pages', 'nowPage', 'totalPage'],
                    methods: {
                        previousPage() {  // 下一頁
                            this.$emit('previousPage');
                        },
                        nextPage() {  // 上一頁
                            this.$emit('nextPage');
                        },
                        skipPage(pageNum) { // 跳頁
                            this.$emit('skipPage', pageNum);
                        },
                    },
                    template: `
      <ul class="pagination" id="pageid">
      
        <!-- <li class="page-item" v-if="nowPage > 0" @click.prevent="previousPage"><a class="page-link" href="#">Previous</a></li> -->
        
        <li class="page-item" :class="{'disabled' : nowPage === 0 }" @click.prevent="previousPage"><a class="page-link" href="#">Previous</a></li>
        
        <li class="page-item" :class="{'active' : nowPage === index}" v-for="(item, index) in pages" :key="index + 1" @click.prevent="skipPage(index)"><a class="page-link" href="#" data-page="{{index + 1}}">{{index + 1}}</a></li>
        
        <li class="page-item" :class="{'disabled' : nowPage === totalPage - 1}" @click.prevent="nextPage"><a class="page-link" href="#">Next</a></li>

        <!-- <li class="page-item" v-if="nowPage < totalPage - 1" @click.prevent="nextPage"><a class="page-link" href="#">Next</a></li> -->
        
      </ul>
  `,
                })
                app.mount('#app');


            </script>

</body>

</html>