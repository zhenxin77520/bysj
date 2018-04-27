<#assign uuid=.now?string["hhmmSSsss"]/>

<div class="box box-info load-content">
  <div class="box-header with-border">
    <h3 class="box-title">个人信息</h3>
  </div>
  <!-- /.box-header -->
  <!-- form start -->

  <#include "/emp/emp_edit.ftl">

</div>
<script>

    $("#form${uuid}").submit(function () {

        var data = $(this).serialize();
        jQuery.ajax({
            type: "post",
            data: data,
            url: "/invitePost/newApply",
            dataType: "json"
        }).done(function (data) {
            console.log(data);
            if (data.status === 200) {
                messageBox("保存成功！");
                showLab('/invitePost/interview', '招聘管理', '面试管理');
            }
        });
        return false;
    });
</script>