<template>
  <div>
    <section id="hero" class="d-flex align-items-center justify-content-center">
      <div class="container" data-aos="fade-up">
        <div
          class="row justify-content-center"
          data-aos="fade-up"
          data-aos-delay="150"
        >
          <div class="col-xl-6 col-lg-8">
            <h1>Powerful Digital Hardware With OverClock<span>.</span></h1>
            <h2>우리는 최고의 상품을 최적의 가격에 판매합니다</h2>
          </div>
        </div>
      </div>
    </section>
    <!-- End Hero -->
    <main id="main">
      <section class="ftco-section ftco-cart">
        <div class="container">
          <div class="row">
            <div class="col-md-12 ftco-animate">
              <div class="cart-list">
                <table class="table">
                  <thead class="thead-primary">
                    <tr class="text-center">
                      <th>글 번호</th>
                      <th>사진</th>
                      <th>제목</th>
                      <th>작성일</th>
                      <th>조회수</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      class="text-center"
                      v-for="(list, i) in state.lists"
                      :key="(list, i)"
                    >
                      <td class="price">{{ list.id }}</td>
                      <td class="image-prod"><img src="" /></td>
                      <td class="product-name">
                        <h3>
                          <a href="/freedetail">{{ list.title }}</a>
                        </h3>
                      </td>
                      <td class="price">{{ list.regDate }}</td>
                      <td class="price">{{ list.viewCount }}</td>
                    </tr>
                    <!-- END TR-->
                  </tbody>
                </table>
              </div>
              <router-link to="/freepost" class="btn btn-primary">
                글쓰기</router-link
              >
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
  <Contact/>
</template>

<script>
import { onMounted } from "vue";
import { reactive } from "@vue/reactivity";
import axios from "axios";
import Contact from "@/components/Contact.vue";

export default {
    name: "ToFree",
    setup() {
        onMounted(() => {
            handleGetList();
        });
        const state = reactive({
            lists: "",
        });
        const handleGetList = async () => {
            const url = "/api/getlist";
            const headers = {
                "Content-Type": "application/json",
            };
            const body = { email: state.email };
            await axios.post(url, body, { headers }).then(function (res) {
                if (res.status === 200) {
                    console.log(res);
                    state.lists = res.data;
                }
            });
        };
        return { state, handleGetList };
    },
    components: { Contact }
};
</script>

<style scoped>
.btn-primary {
  float: right;
}
#hero:before {
  height: 500px;
}
#hero {
  height: inherit;
}
</style>
