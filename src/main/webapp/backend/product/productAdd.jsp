<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/backend/share.jsp"%>
<!-- 匯入java -->
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.ProductVO"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.gamecompany.model.*"%>
<%@ page import="com.gametype.model.*"%>
<%@ page import="com.gameplatformtype.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) 存入req的orderVO物件 (包括幫忙取出的orderVO, 也包括輸入資料錯誤時的orderVO物件)
GameCompanyService gameCompanyService = new GameCompanyService();
List<GameCompanyVO> gameCompanyList = gameCompanyService.getAll();
pageContext.setAttribute("gameCompanyList",gameCompanyList);

GameTypeService gameTypeService = new GameTypeService();
List<GameTypeVO> gameTypeList = gameTypeService.getAll();
pageContext.setAttribute("gameTypeList",gameTypeList);

GamePlatformTypeService gamePlatformTypeService = new GamePlatformTypeService();
List<GamePlatformTypeVO> gamePlatformTypeList = gamePlatformTypeService.getAll();
pageContext.setAttribute("gamePlatformTypeList",gamePlatformTypeList);

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

<title>新增商城產品內容</title>



<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/3.0.2/vue.global.js"></script>

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
			<%=productVO == null%>
			--${productVO.productName}--
			<h3>
				<i class="fa fa-angle-right"></i> 商品新增
			</h3>
			<div class="row mt">
				<div class="col-lg-12">
					<div id="app">
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">剛剛輸入有誤，請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<h3>
							<i class="fa fa-angle-right"></i> 最近新增前三筆
						</h3>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>產品編號</th>
									<th>產品名稱</th>
									<th>價格</th>
									<th>封面</th>
									<th>遊戲種類</th>
									<th>遊戲平台種類</th>
									<th>遊戲公司</th>
									<th>銷售狀態</th>
									<th>產品描述</th>
									<th>UpcNum</th>
									<th>產品圖片2</th>
									<th>產品圖片3</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="item in datastore" :key="item.ProductNo"
									v-bind:class="{'table-success':item.onStock}">
									<td>{{item.productNo}}</td>
									<td>{{item.productName}}</td>
									<td>{{item.productPrice}}</td>
									<td><img :src="item.picList[0].imageUrl" alt=""
										height="100"></td>
									<td>{{item.gameTypeNo}}</td>
									<!-- 要改成中文 -->
									<td>{{item.gamePlatformNo}}</td>
									<td>{{item.gameCompanyNo}}</td>
									<td>{{item.productState}}</td>
									<td>{{item.itemProdDescription}}</td>
									<td>{{item.upcNum}}</td>
									<td><img :src="item.picList[1].imageUrl" alt=""
										height="100"></td>
									<td><img :src="item.picList[2].imageUrl" alt=""
										height="100"></td>
								</tr>
							</tbody>
						</table>
						<!--   v-if="isNew" 判斷要不要打開編輯的表單  -->
						<!--   form內容要進去資料庫  -->
						<div style="width: 30%;">
							<!-- 							<form v-show="isNew" method="post" -->
							<form method="post" action="/CGA101G1/product/GetParameter"
								enctype="multipart/form-data" required>
								<!-- <form method="post" action="GetParameter" enctype="multipart/form-data"> -->
								<div>
									<label for="productNo" class="form-label">產品編號</label> <input
										type="text" id="productNo" name="ProductNo"
										class="form-control" v-model="temp.productNo" readonly>
								</div>
								<div>
									<label for="productName" class="form-label">產品名稱</label> <input
										type="text" id="productName" name="ProductName"
										class="form-control" value="${productVO.productName}"
										@change="checkdata($event)">
								</div>
								<div>
									<label for="productPrice" class="form-label">價格</label> <input
										type="number" id="productPrice" name="ProductPrice"
										class="form-control" value="${productVO.productPrice}">
								</div>
								<!-- 要改 -->
								<div>
									<label for="productState" class="form-label">銷售狀態</label> <input
										type="radio" id="productStateUn" name="ProductState"
										v-model="temp.productStateUn" value="0" checked> <label
										for="productStateUn" class="form-label">未上架</label> <input
										type="radio" id="productStateS" name="ProductState"
										v-model="temp.productStateS" value="1"> <label
										for="productStateS" class="form-label">已上架</label>
								</div>
								<div>
									<label for="gameTypeNo" class="form-label">遊戲種類</label> <select
										name="GameTypeNo" id="gameTypeNo"
										value="${productVO.gameTypeNo}" required>
										<c:forEach var="gameTypeVO"
											items="${gameTypeList}">
											<option value="${gameTypeVO.gameTypeNo}">${gameTypeVO.gameTypeName}</option>
										</c:forEach>
									</select>
									<!-- 
                                            <input type="number" id="gameTypeNo" name="GameTypeNo" class="form-control"
                                                v-model="temp.gameTypeNo"> -->
								</div>
								<div>
									<label for="gamePlatformNo" class="form-label">遊戲平台種類</label> <select
										name="GamePlatformNo" id="gamePlatformNo"
										value="${productVO.gamePlatformNo}" required>
										<c:forEach var="gamePlatformTypeVO"
											items="${gamePlatformTypeList}">
											<option value="${gamePlatformTypeVO.gamePlatformNo}">${gamePlatformTypeVO.gamePlatformName}</option>
										</c:forEach>
									</select>

								</div>
								<div>
									<label for="gameCompanyNo" class="form-label">遊戲公司</label> 
									<select
										name="GameCompanyNo" id="gameCompanyNo"
										value="${productVO.gameCompanyNo}" required>
										<c:forEach var="gameCompanyVO"
											items="${gameCompanyList}">
											<option value="${gameCompanyVO.gameCompanyNo}">${gameCompanyVO.gameCompanyName}</option>
										</c:forEach>
									</select>

								</div>
								<div>
									<label for="upcNum" class="form-label">UpcNum</label> <input
										type="text" id="upcNum" name="UpcNum" class="form-control"
										value="${productVO.upcNum}">
								</div>
								<div>
									<label for="itemProdDescription" class="form-label">遊戲描述</label>
									<textarea name="ItemProdDescription" id="itemProdDescription"
										cols="55" rows="20" value="${productVO.itemProdDescription}"></textarea>
								</div>

								<div>
									<label for="productImage1" class="form-label">封面照片</label> <img
										v-if="url" :src="url" class="img-fluid d-block" alt=""
										width="100"> <input type="file" id="productImage1"
										name="productImage1" class="form-control"
										v-on:change="onFileChange">
								</div>
								<div>
									<label for="productImage2" class="form-label">產品圖片2</label> <img
										v-if="url2" :src="url2" class="img-fluid d-block" alt=""
										width="100"> <input type="file" id="productImage2"
										name="productImage2" class="form-control"
										v-on:change="onFileChange2">
								</div>
								<div>
									<label for="productImage3" class="form-label">產品圖片3</label> <img
										v-if="url3" :src="url3" class="img-fluid d-block" alt=""
										width="100"> <input type="file" id="productImage3"
										name="productImage3" class="form-control"
										v-on:change="onFileChange3">

								</div>

								<input type="SUBMIT" v-on:click="confirmEdit">
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

	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!--script for this page-->

	<script>
                //custom select box

                // $(function () {
                //     $('select.styled').customSelect();
                // });

        const App = {
            data() {
                return {
                    datastore: [],
                    picData: [],
                    temp: {},
                    isNew: false,
                    url: null,
                    url2: null,
                    url3: null,
                }
            },
            methods: {
                getProductData() {
                    const apiUrl = '/CGA101G1/product/getNewestProduct';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取ProductData的狀態：" + response.status); //狀態碼

                            this.datastore = response.data;//塞進去
                            console.log("datastore讀取資料ok");
                            this.getPicData();
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
                additem() {
                    this.isNew = true;
                    this.temp = {};
                    this.url = null;
                    this.url2 = null;
                    this.url3 = null;
                },
                edit(para) {
                    this.temp = {};
                    this.temp = { ...para };//淺層拷貝，把點選編輯的物件放進temp
                    this.url = this.temp.picList[0].imageUrl;
                    this.url2 = this.temp.picList[1].imageUrl;
                    this.url3 = this.temp.picList[2].imageUrl;

                },
                onFileChange(e) {
                    this.url = "";
                    const file = e.target.files[0];
                    this.url = URL.createObjectURL(file);
                },
                onFileChange2(e) {
                    this.url2 = "";
                    const file2 = e.target.files[0];
                    this.url2 = URL.createObjectURL(file2);
                },
                onFileChange3(e) {
                    this.url3 = "";
                    const file3 = e.target.files[0];
                    this.url3 = URL.createObjectURL(file3);
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


                confirmEdit() {
                }

            },
            created() {
                this.getProductData();

            }
        };

        Vue.createApp(App).mount('#app');

            </script>

</body>

</html>