<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/backend/share.jsp"%>
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
						<!-- <div class="text-end mx-3">
                                <button type="button" class="btn btn-primary" v-on:click="additem">新增</button>
                            </div> -->

						<card-template :read-data="renderData" :now-page="nowPage"
							v-on:emitedit="edit"> </card-template>
						<nav class="d-flex justify-content-center">
							<pagination-list :pages="renderData" :now-page="nowPage"
								:total-page="totalPage" @skip-page="skipPage"
								@previous-page="previousPage" @next-page="nextPage">
							</pagination-list>
						</nav>
						<!--   v-if="isNew" 判斷要不要打開編輯的表單  -->
						<!--   form內容要進去資料庫  -->
						<div style="width: 30%;">
							<form v-if="isNew || temp.productNo" method="post"
								action="/CGA101G1/product/GetParameter"
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
										class="form-control" v-model="temp.productName"
										@change="nameCheck($event)">
									<div style="color: red;"
										:style="{visibility: nameInput ? 'visible' : 'hidden'}">
										請輸入正確格式的名字，不應包含特殊符號+-/^~{}@$!%*?& 或是只填空格!!</div>
								</div>
								<div>
									<label for="productPrice" class="form-label">價格</label> <input
										type="number" id="productPrice" name="ProductPrice"
										class="form-control" v-model="temp.productPrice"
										@change="priceCheck($event)">
									<div style="color: red;"
										:style="{visibility: priceInput ? 'visible' : 'hidden'}">
										價格應該落在99~99,999元之間!!</div>
								</div>
								<!-- 要改 -->
								<div>
									<label for="productState" class="form-label">銷售狀態</label> <input
										type="radio" id="productStateUn" name="ProductState"
										v-model="temp.productState" value="0" checked=""> <label
										for="productStateUn" class="form-label">未上架</label> <input
										type="radio" id="productStateS" name="ProductState"
										v-model="temp.productState" value="1"> <label
										for="productStateS" class="form-label">已上架</label>
								</div>
								<div>
									<label for="gameTypeNo" class="form-label">遊戲種類</label> <select
										name="GameTypeNo" id="gameTypeNo" v-model="temp.gameTypeNo"
										required>
										<option value="63001">動作</option>
										<option value="63002">動作冒險</option>
										<option value="63003">冒險</option>
										<option value="63004">大型多人在線</option>
										<option value="63005">角色扮演</option>
										<option value="63006">模擬</option>
										<option value="63007">戰略</option>
										<option value="63008">交通工具模擬</option>
										<option value="63009">運動</option>
										<option value="63010">射擊</option>
									</select>
								</div>
								<div>
									<label for="gamePlatformNo" class="form-label">遊戲平台種類</label> <select
										name="GamePlatformNo" id="gamePlatformNo"
										v-model="temp.gamePlatformNo" required>
										<option value="64001">switch</option>
										<option value="64002">PS5</option>
										<option value="64003">XBOX</option>
										<option value="64004">PC</option>
									</select>
								</div>
								<div>
									<label for="gameCompanyNo" class="form-label">遊戲公司</label> <select
										name="GameCompanyNo" id="gameCompanyNo"
										v-model="temp.gameCompanyNo" required>
										<option value="61001">Nintendo</option>
										<option value="61002">Imagineer</option>
										<option value="61003">2K</option>
										<option value="61004">SEGA</option>
										<option value="61005">Capcom</option>
										<option value="61006">Sony Entertainment</option>
										<option value="61007">Xbox Game Studios</option>
										<option value="61008">Ubisoft</option>
										<option value="61009">Saber Interactive</option>
									</select>
								</div>
								<div>
									<label for="upcNum" class="form-label">UpcNum</label> <input
										type="number" id="upcNum" name="UpcNum" class="form-control"
										v-model="temp.upcNum" required @change="upcNumCheck($event)" maxlength="50">
									<div style="color: red;"
										:style="{visibility: upcNumInput ? 'visible' : 'hidden'}">
										upcNum不應該都是0  !!</div>
								</div>
								<div>
									<label for="itemProdDescription" class="form-label">遊戲描述</label>
									<textarea name="ItemProdDescription" id="itemProdDescription"
										cols="55" rows="20" required>{{temp.itemProdDescription}}</textarea>
								</div>


								<div>
									<label v-if="url2" class="form-label">封面照片編號</label> <input
										v-if="url2" type="text" name="productImage1NO"
										class="form-control" v-model="temp.picList[0].productPicNo"
										readonly>
								</div>
								<div>
									<label for="productImage1" class="form-label">封面照片</label> <img
										v-if="url" :src="url" class="img-fluid d-block" alt=""
										width="100"> <input type="file" id="productImage1"
										name="productImage1" class="form-control"
										v-on:change="onFileChange">
								</div>

								<div>
									<label v-if="url2" class="form-label">產品圖片2編號</label> <input
										v-if="url2" type="text" name="productImage2NO"
										class="form-control" v-model="temp.picList[1].productPicNo"
										readonly>
								</div>
								<div>
									<label for="productImage2" class="form-label">產品圖片2</label> <img
										v-if="url2" :src="url2" class="img-fluid d-block" alt=""
										width="100"> <input type="file" id="productImage2"
										name="productImage2" class="form-control"
										v-on:change="onFileChange2">
								</div>

								<div>
									<label v-if="url3" class="form-label">產品圖片3編號</label> <input
										v-if="url3" type="text" name="productImage3NO"
										class="form-control" v-model="temp.picList[2].productPicNo"
										readonly>
								</div>
								<div>
									<label for="productImage3" class="form-label">產品圖片3</label> <img
										v-if="url3" :src="url3" class="img-fluid d-block" alt=""
										width="100"> <input type="file" id="productImage3"
										name="productImage3" class="form-control"
										v-on:change="onFileChange3">

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
                const app = Vue.createApp({
                    data() {
                        return {
                            datastore: [],
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
                         




                            temp: {},
                            isNew: false,
                            url: null,
                            url2: null,
                            url3: null,
                            //驗證表單用
                            nameInput: false,
                            priceInput: false,
                            upcNumInput:false,
                            
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
                                    (this.datastore.slice
                                        (n * this.pageBaseNum, n * this.pageBaseNum + this.pageBaseNum)
                                    )
                                );
                            }
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
                        additem() {
                            this.isNew = true;
                            this.temp = {};
                            this.url = null;
                            this.url2 = null;
                            this.url3 = null;
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
                            const rules = new RegExp('[#+-/^~{}@$!%*?&]');
                            if (rules.test(e.target.value) || (e.target.value).trim().length == 0) {
                                this.nameInput = true;
                                this.btn=true;
                            }else{
                            	this.btn=false;
                            }
                        },
                        priceCheck(e) {
                            this.priceInput = false;
                            if ((e.target.value).trim().length >= 5 || (e.target.value).trim().length <= 2) {
                                this.priceInput = true;
                                this.btn=true;
                            }else{
                            	this.btn=false;
                            }                           
                        },
                        upcNumCheck(e){
                        	this.upcNumInput = false;
                            const rules = /^[0]{1,50}$/;
                            if (rules.test(e.target.value) || (e.target.value).trim().length == 0) {
                                this.upcNumInput = true;
                                this.btn=true;
                            }else{
                            	this.btn=false;
                            }
                        }


                    },
                    computed: {
                        totalPage() {
                            return Math.ceil(this.datastore.length / this.pageBaseNum); // 依每頁數量計算總頁數
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
                                        <th>編輯</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in readData[nowPage]" :key="item.productNo"
                                        v-bind:class="{'table-success':item.onStock}">
                                        <td>{{item.productNo}}</td>
                                        <td>{{item.productName}}</td>
                                        <td>{{item.productPrice}}</td>
                                        <td>
                                            <img :src="item.picList[0].imageUrl" alt="" height="100">
                                        </td>
                                        <td>{{item.gameTypeName}}</td>
                                        <!-- 要改成中文 -->
                                        <td>{{item.gamePlatformName}}</td>
                                        <td>{{item.gameCompanyNo}}</td>
                                        <td>{{item.StatusName}}</td>
                                        <td class="descriptionArea">{{item.itemProdDescription}}</td>
                                        <td>{{item.upcNum}}</td>
                                        <td>
                                            <img :src="item.picList[1].imageUrl" alt="" height="100">
                                        </td>
                                        <td>
                                            <img :src="item.picList[2].imageUrl" alt="" height="100">
                                        </td>
                                        <td><button type="button" class="btn btn-outline-primary"
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








        // const App = Vue.createApp({
        //     data() {
        //         return {
        //             datastore: [],
        //             picData: [],
        //             temp: {},
        //             isNew: false,
        //             url: null,
        //             url2: null,
        //             url3: null,
        //         }
        //     },
        //     methods: {
        //         getProductData() {
        //             const apiUrl = 'http://localhost:8081/CGA101G1/product/OneProductToJson';
        //             axios.get(apiUrl)
        //                 // 一定要用箭頭函示!!!!
        //                 .then((response) => {
        //                     console.log("取ProductData的狀態：" + response.status); //狀態碼

        //                     this.datastore = response.data;//塞進去
        //                     console.log("datastore讀取資料ok");
        //                     this.getPicData();
        //                 });
        //         },
        //         getPicData() {
        //             const apiUrl = 'http://localhost:8081/CGA101G1/product/ProductPics';
        //             axios.get(apiUrl)
        //                 // 一定要用箭頭函示!!!!
        //                 .then((response) => {
        //                     console.log("取pic的狀態：" + response.status); //狀態碼

        //                     this.picData = response.data;//塞進去
        //                     this.insertPic(this.picData);

        //                 });
        //         },
        //         additem() {
        //             this.isNew = true;
        //             this.temp = {};
        //             this.url = null;
        //             this.url2 = null;
        //             this.url3 = null;
        //         },
        //         edit(para) {
        //             this.temp = {};
        //             this.temp = { ...para };//淺層拷貝，把點選編輯的物件放進temp
        //             this.url = this.temp.picList[0].imageUrl;
        //             this.url2 = this.temp.picList[1].imageUrl;
        //             this.url3 = this.temp.picList[2].imageUrl;

        //         },
        //         onFileChange(e) {
        //             const file = e.target.files[0];
        //             this.url = URL.createObjectURL(file);
        //         },
        //         onFileChange2(e) {
        //             const file2 = e.target.files[0];
        //             this.url2 = URL.createObjectURL(file2);
        //         },
        //         onFileChange3(e) {
        //             const file3 = e.target.files[0];
        //             this.url3 = URL.createObjectURL(file3);
        //         },
        //         insertPic(picData) {
        //             console.log("insertPic開始");
        //             for (j = 0; j < this.datastore.length; j++) {
        //                 this.datastore[j].picList = [];
        //                 for (i = 0; i < picData.length; i++) {
        //                     if (picData[i].productNo == this.datastore[j].productNo) {
        //                         this.datastore[j].picList.push(picData[i]);
        //                     }
        //                 }
        //             }
        //             this.picData = [];
        //             console.log("insert完畢");
        //         },


        //         checkdata(e) {
        //             console.log(e);
        //             if ((e.target.value).trim().length() == 0) {
        //                 console.log("不能空值或是空格")
        //             }
        //         }

        //     },
        //     created() {
        //         this.getProductData();

        //     }
        // });
        // App.mount('#app');

            </script>

</body>

</html>