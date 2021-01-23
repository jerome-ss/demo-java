<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
function add(){
  var oTr = document.getElementById("addtr").rows[1]; // 获取要克隆的行
  var newTr = oTr.cloneNode(true); // 克隆一个新的tr标签
  document.getElementById("addtr").getElementsByTagName("tbody")[0].appendChild(newTr);
  //document.getElementById("addtr").parentNode.appendChild(newTr);
  newTr.cells[0].firstChild.value = newTr.rowIndex;
  newTr.cells[0].firstChild.name = 'brand'+newTr.rowIndex;
 //document.getElementById("b1").disabled = newTr.rowIndex ==5 ;
}
</script>
</head>
<body>

<input type="button" id="b1" value="添加一行" onclick="add()" />
<table width="700" border="0" cellspacing="0" cellpadding="0" id="addtr">
  <tr>
    <td height="30" align="center" bgcolor="#CCCCCC">ID</td>
    <td align="center" bgcolor="#CCCCCC">Username</td>
    <td align="center" bgcolor="#CCCCCC">Usertype</td>
    <td align="center" bgcolor="#CCCCCC">Other</td>
  </tr>
  <tr>
    <td height="30" align="center"><input type="text" size="2" value="1" /></td>
    <td align="center"><input type="text" name="username" /></td>
    <td align="center">
      <select name="type">
        <option value="1">Administrator</option>
        <option value="2">Guest</option>
        <option value="3">zzjs.net</option>
        <option value="4">站长特效网</option>
      </select>
    </td>
    <td align="center"><input type="text" name="username2" /></td>
  </tr>
</table>
</body>
</html>