<template>
	<Login class="d-flex justify-content-center align-items-center">
		<div class="login  p-5 ">
			<div class="loginlogo text-center"><img class="img-fluid px-5" src="#" alt="" srcset=""></div>
			

			<div class="loginmenu d-flex justify-content-center justalign-item-center w-100">
				<ul class="list-group list-group-horizontal w-100 px-5 my-4 overflow-hidden">
					<a v-on:click="changeform('false')"
						class="list-group-item w-50 border border-0 border-end text-end">사이트 로그인</a>
				</ul>
			</div>
			<div v-if="!(state.form == true)">
				<SiteLogin></SiteLogin>
			</div>
			<div v-else>
      </div>

			<div class="utlpart d-flex justify-content-center align-items-center gap-3 my-3">
				<router-link to="/join"><span>회원가입</span></router-link>
			</div>
		</div>
	</Login>
</template>
<script >
import SiteLogin from "../components/Login.vue";
import { reactive } from "vue";
// import router from "@/router";
import axios from "axios";
import store from "@/store";
import router from "@/router";
export default {
	components: { SiteLogin},
  setup() {

		let state = reactive({
			form: "true"
		})
		function changeform(a) {
			if (a == "true") {
				state.form = true

			} else if (a == 'false') {
				state.form = false
			}
			console.log(state.form);
		}

    //google

    let userData;
    const callback = (response) => {
      userData = decodeCredential(response.credential);
      // const login = JSON.parse(userData)

      // const notDecode = response.credential;
      console.log("Handle the response", response);
      console.log("DecodeCredential", userData);
      console.log(userData.email +"------"+userData.sub);
      // console.log(notDecode);

      const url = "/members/login"
      const headers = { "Content-Type": "application/json; charset=utf-8;"}
      const body = { email: userData.email, password: userData.sub};
      try{
         axios.post(url, body, {headers}).then(function(res){
          store.commit('setToken',res.data.token);
          store.commit('setEmail',res.data.email);
          store.commit('setId',res.data.id);
          console.log(res.data)
          alert('로그인되었습니다.')
          router.push(`/`)
        })
      }
      catch(err){
        alert('로그인에 실패하였습니다.')
      }
    };

    const buttonConfig = {
      type: "standard",
      theme: "outline",
      size: "large",
      text: "signup_with",
      // shape: "",
      // logo_alignment: "",
      // width: "",
      // locale: "",
    };





    return { state, changeform, callback, buttonConfig };
	},
};
</script>

<style lang=""></style>
