


<style>
    .detail-indent {
        text-indent: 2em;
    }
</style>

    <div class="box-body">
        <div class="row">
            <div class="col-sm-2 text-center">
                <img class="img-responsive img-circle center-block" src="<#if emp.img?? && emp.img != "">${imgServer}${emp.img}<#else >dist/img/user_img.jpg</#if>" alt="User profile picture">
            </div>
            <div class="col-sm-5">
                <ul>
                    <li><label>工号：</label>${emp.id}</li>
                    <li><label>姓名：</label>${emp.name}</li>
                    <li><label>年龄：</label>${emp.age}</li>
                    <li><label>性别：</label>${emp.gender}</li>
                    <li><label>部门：</label>${department.name}</li>
                    <li><label>入职时间：</label>
                        <#if emp.joinTime??>
                            ${emp.joinTime?string('yyyy-MM-dd')}
                        </#if>
                    </li>
                </ul>
            </div>
            <div class="col-sm-5">
                <ul>
                    <li><label>民族：</label>${emp.nation}</li>
                    <li><label>政治面貌：</label>${emp.politics}</li>
                    <li><label>生日：</label>${emp.birthday?string('yyyy-MM-dd')}</li>
                    <li><label>电话：</label>${emp.phone}</li>
                    <li><label>邮箱：</label>${emp.email}</li>
                    <li><label>学历：</label>${emp.education!}</li>
                </ul>
            </div>
        </div>
        <hr>
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <b>家庭住址：</b><br>
                    <p class="detail-indent">${emp.address!}</p>
                </div>
                <div class="col-sm-6">
                    <b>政治面貌：</b><br>
                    <p class="detail-indent">${emp.politics!}</p>
                </div>
            </div>
    </div>