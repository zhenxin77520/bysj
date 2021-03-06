<#assign uuid=.now?string["hhmmSSsss"]/>
<link rel="stylesheet" href="/plugins/select2/select2.min.css">

<div class="box box-primary load-content">
    <div class="box-header with-border">
        <h3 class="box-title">新建调度</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form role="form" id="form${uuid}">
        <div class="box-body">

            <div class="form-group">
                <label>选择部门：</label>
                <select name="origin" class="form-control select2" id="originDepartment" style="width: 100%;" required>
                    <option selected>-- 请选择 --</option>
                    <#list departmentList as department>
                        <option value="${department.departmentId}">${department.name!}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label>员工姓名：</label>
                <select name="empId" class="form-control select2" id="empList" style="width: 100%;" title="员工" required>
                    <option selected>-- 请选择 --</option>
                </select>
            </div>

            <div class="form-group">
                <label>转到部门：</label>
                <select name="arrive" class="form-control select2" style="width: 100%;" required>
                    <option selected>-- 请选择 --</option>
                    <#list departmentList as department>
                        <option value="${department.departmentId}">${department.name!}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label>职位：</label>
                <select name="post" class="form-control" style="width: 100%;" required>
                    <#list postList as item>
                        <option>${item.post}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="exampleInputFile">申请原因：</label>
                <textarea name="text" class="form-control" required></textarea>
            </div>

            <div class="form-group">
                <label for="exampleInputFile">备注：</label>
                <textarea name="originComment" class="form-control" required></textarea>
            </div>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-primary">提交</button>
        </div>
    </form>
</div>



<script src="/plugins/select2/select2.full.min.js"></script>
<script>
    $(".select2").select2();

    $("#originDepartment").change(function () {
        var id = $(this).val();
        if (id.trim() === "") return;
        jQuery.ajax({
            url: "/emp/findListByDepartment",
            type: "POST",
            data: {
                departmentId: id
            }
        }).done(function (result) {
            if (result.status === 200) {
                var html = "<option selected>-- 请选择 --</option>";
                $.each(result.data, function (k, v) {
                    html += "<option value='" + v.id + "'>" + v.name + "</option>";
                });
                $("#empList").html(html);
            }
        })
    });

    $("#form${uuid}").submit(function () {

        var formData = $(this).serialize();

        jQuery.ajax({
            url: "/emp/newAdjustment",
            type: "POST",
            data: formData
        }).done(function (result) {
            if (result.status === 200) {
                messageBox("提交成功！");
                // todo
            } else {
                messageBox("提交失败！");
            }
        });

        return false;
    });
</script>