package br.com.alexandre.card.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CardTb")
class CardDto(
    @PrimaryKey
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "enterprise") val enterprise: String,
    @ColumnInfo(name = "ownerFullName") val ownerFullName: String,
    @ColumnInfo(name = "ownerRegNumber") val ownerRegNumber: String,
    @ColumnInfo(name = "expiryMonth") val expiryMonth: Int,
    @ColumnInfo(name = "expiryYear") val expiryYear: Int,
    @ColumnInfo(name = "securityNumber") val securityNumber: String)
