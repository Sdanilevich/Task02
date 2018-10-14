<div class="container">
    <form method="post" action="do?command=searchApartment" style="border: 0px;">
        <div class="row">
            <fieldset>
                <legend>${searchTitle}</legend>

                <div>

                    <div style="display: inline-block; ">
                        <input type="text" class="form-control" id="dateBegin" value="${dateBegin}"name="dateBegin" placeholder="Дата c" required>
                    </div>

                    <div style="display: inline-block; ">
                        <input type="text" class="form-control" value="${dateEnd}"id="dateEnd" name="dateEnd" placeholder="Дата по" required>

                    </div>
                </div>
                <br/>
<c:choose>
    <c:when test="${local=='En'}">
                <div style="display: inline-block; ">
                    <label for="country">${searchCountryTitle}</label>
                    <div>
                        <select id="country" name="country">
                            <option value = "0"></option>
                            <c:forEach items="${listCountry}" var="country" >
                                <option value = ${country.id}>${country.nameEn}</option>

                            </c:forEach>

                        </select>
                    </div>
                </div>

                <div style="display: inline-block; ">
                    <label  for="city">${searchCityTitle}</label>
                    <div>
                        <select id="city" name="city">
                            <option value = "0"></option>
                            <c:forEach var="city" items="${listCity}">
                                        <option value = ${city.id}>${city.nameEn}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
    </c:when>
    <c:otherwise>
        <div style="display: inline-block; ">
            <label for="country">${searchCountryTitle}</label>
            <div>
                <select id="country" name="country">
                    <option value = "0"></option>
                    <c:forEach items="${listCountry}" var="country" >
                        <option value = ${country.id}>${country.name}</option>

                    </c:forEach>

                </select>
            </div>
        </div>

        <div style="display: inline-block; ">
            <label  for="city">${searchCityTitle}</label>
            <div>
                <select id="city" name="city">
                    <option value = "0"></option>
                    <c:forEach var="city" items="${listCity}">
                        <option value = ${city.id}>${city.name}</option>
                     </c:forEach>
                </select>
            </div>
        </div>
    </c:otherwise>
</c:choose>
                <br/><br/>
                <div >
                    <label  for="signupbutton"></label>
                    <div >
                        <button id="signupbutton" name="signupbutton">${searchButton}</button>
                    </div>
                </div>
            </fieldset>
        </div>

    </form>
</div>
