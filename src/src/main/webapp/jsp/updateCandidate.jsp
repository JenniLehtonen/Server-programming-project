<%@ include file="../header.html" %>
<link rel="stylesheet" href="koodit.css">

<h2>Muokkaa ehdokkaan tietoja</h2>
<br>
<form action='/updatecandidate' method='post'>
<b> Candidate's id </b> <br>
<input type="text" name='ehdokas_id' value='${requestScope.candidate.ehdokas_id}' readonly class='input'> <br>
<b> Sukunimi </b> <br>
<input type="text" name='sukunimi' value='${requestScope.candidate.sukunimi}' class='input'> <br>
<b> Etunimi </b> <br>
<input type="text" name='etunimi' value='${requestScope.candidate.etunimi}' class='input'> <br>
<b>Puolue </b> <br>
<input type="text" name='puolue' value='${requestScope.candidate.puolue}' class='input'> <br>
<b>Kotipaikkakunta </b> <br>
<input type="text" name='kotipaikkakunta' value='${requestScope.candidate.kotipaikkakunta}' class='input'> <br>
<b>Ik‰</b> <br>
<input type="text" name='ika' value='${requestScope.candidate.ika}' class='input'> <br>
<b> Ammatti </b> <br>
<input type="text" name='ammatti' value='${requestScope.candidate.ammatti}' class='input'> <br> 
<b>Miksi sinut tulisi valita eduskuntaan? </b><br>
<input type="text" name='miksi_eduskuntaan' value='${requestScope.candidate.miksi_eduskuntaan}' class='input'> <br>
<b>Mit‰ asioita haluaisit edist‰‰?</b> <br>
<input type="text" name='mita_asioita_haluat_edistaa' value='${requestScope.candidate.mita_asioita_haluat_edistaa}' class='input'> <br><br>
<input type='Submit' name='ok' value='Send' class='dropbtn'> 

</form>

<%@ include file="../footer.html" %>
