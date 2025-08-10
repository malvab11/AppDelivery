package com.example.appdelivery.presentation.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> = _name
    private val _lastName = MutableLiveData<String>("")
    val lastName: LiveData<String> = _lastName
    private val _documentId = MutableLiveData<String>("")
    val documentId: LiveData<String> = _documentId
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>("")
    val password: LiveData<String> = _password
    private val _cellphone = MutableLiveData<String>("")
    val cellphone: LiveData<String> = _cellphone
    private val _isHide = MutableLiveData<Boolean>(true)
    val isHide: LiveData<Boolean> = _isHide
    private val _isChecked = MutableLiveData<Boolean>(false)
    val isChecked: LiveData<Boolean> = _isChecked
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isEnabled = MutableLiveData<Boolean>(false)
    val isEnabled: LiveData<Boolean> = _isEnabled

    fun onNameChange(name: String) {
        _name.value = name
        updateButtonState()
    }

    fun onLastNameChange(lastName: String) {
        _lastName.value = lastName
        updateButtonState()
    }

    fun onDocumentIdChange(documentId: String) {
        _documentId.value = documentId
        updateButtonState()
    }

    fun onEmailChange(email: String) {
        _email.value = email
        updateButtonState()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        updateButtonState()
    }

    fun onCellPhoneChage(cellphone: String) {
        _cellphone.value = cellphone
        updateButtonState()
    }

    fun onVisibilityClick() {
        _isHide.value = !_isHide.value
    }

    fun onCheckChange(){
        _isChecked.value = !_isChecked.value
        updateButtonState()
    }

    private fun updateButtonState() {
        _isEnabled.value = _email.value?.contains("@") == true &&
                (_password.value?.length ?: 0) >= 6 &&
                _name.value.isNotEmpty() &&
                _lastName.value.isNotEmpty() &&
                (_documentId.value.length >= 8) &&
                (_cellphone.value.length == 9) &&
                _isChecked.value
    }

}