package com.hongwei.model.arcgis.covid19

data class BeanByAuState(
        var objectIdFieldName: String,
        var uniqueIdField: UniqueIdField,
        var globalIdFieldName: String,
        var fields: Array<Fields>,
        var features: Array<Features>
)

data class UniqueIdField(
        var name: String,
        var isSystemMaintained: Boolean = false
)

data class Fields(
        var name: String?,
        var type: String?,
        var alias: String?,
        var sqlType: String?,
        var length: Long = 0,
        var domain: String? = null,
        var defaultValue: String? = null,
        var description: String = ""
)