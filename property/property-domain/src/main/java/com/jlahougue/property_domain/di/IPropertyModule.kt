package com.jlahougue.property_domain.di

import com.jlahougue.property_domain.repository.IPropertyRepository

interface IPropertyModule {
    val repository: IPropertyRepository
}