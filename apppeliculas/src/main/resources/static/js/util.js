const addElement = (e, idSelector, idContenedor, idsStr) => {
    let _selectorEl = document.getElementById(`${idSelector}`)
    let _contenedorEl = document.getElementById(`${idContenedor}`)
    let _idsStrEl = document.getElementById(`${idsStr}`)
    let listIds = _idsStrEl.value.toString().split("|").map(el => parseInt(el)).filter(el => !isNaN(parseInt(el)))
    let idCurrent = parseInt(_selectorEl.value)

    if (_selectorEl && idCurrent !== 0) {
        if(listIds.filter(el => el === idCurrent).length === 0) {
            let _selectedOption = _selectorEl.options[_selectorEl.selectedIndex]
            let _p = document.createElement("p")
            _p.setAttribute("class", "row w-auto rounded-4")

            let _sText = document.createElement("span")
            _sText.setAttribute("class", "w-auto")
            _sText.innerText = `${_selectedOption.text}`
            _p.appendChild(_sText)

            let _sDelete = document.createElement("span")
            _sDelete.setAttribute("class", "w-auto")
            _sDelete.innerText = `X`
            _p.appendChild(_sDelete)

            _contenedorEl.appendChild(_p)
            listIds.push(idCurrent)
            _idsStrEl.value = listIds.join("|")
        }
    }
    _selectorEl.selectedIndex = null
}

const deleteElement = (e, idContenedor, idsStr) => {
    let _contenedorEl = document.getElementById(`${idContenedor}`)
    let _idsStrEl = document.getElementById(`${idsStr}`)
    let _idDelete = parseInt(e.getAttribute("data-id"))
    let listIds = _idsStrEl.value.toString().split("|").map(el => parseInt(el))

    if (_idDelete !== 0) {
        _idsStrEl.value = listIds.filter(el => el !== _idDelete).join("|")
        let _p = _contenedorEl.getElementsByTagName("p")
        for (let i=0; i < _p.length; i++) {
            if(parseInt(_p[i].getAttribute("data-id"))===_idDelete){
                _p[i].remove()
                break
            }
        }
    }
}