<template>
  <section id="hero" class="d-flex align-items-center justify-content-center">
    <div class="input-form col-md-12 mx-auto form-floating">
      <div class="top-margin">
        <div class="mb-4">
          <span class="home-title">글 등록</span>
        </div>
      </div>
      <div>
        <input
          type="text"
          placeholder="제목"
          class="form-control my-3 rounded-0 title form-floating"
          v-model="title"
          id="title"
        />
        <ckeditor
          @ready="onReady"
          :editor="editor"
          v-model="editorData"
          :config="editorConfig"
        ></ckeditor>
        <div class="tagwarning" ref="warning"></div>
        <button
          class="btn btn-primary btn4"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasBottom"
          aria-controls="offcanvasBottom"
        >
          등록
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import CKEditor from "@ckeditor/ckeditor5-vue";

export default {
  name: "CKEditor2",
  components: {
    ckeditor: CKEditor.component,
  },
  data: () => ({
    title: "",
    editor: ClassicEditor,
    editorConfig: {
      // The configuration of the editor.
      height: "500px",
      language: "ko",
    },
  }),
  methods: {
    onReady: function onReady(editor) {
      editor.ui
        .getEditableElement()
        .parentElement.insertBefore(
          editor.ui.view.toolbar.element,
          editor.ui.getEditableElement()
        );
    },
  },

  // const removeTag = (index) => {
  // tags.splice(index, 1);
  // };
};
</script>

<style>
.btn4 {
  margin-top: 10px;
}
.ck-editor__editable_inline {
  max-height: 50vh;
  height: 50vh;
}
</style>
