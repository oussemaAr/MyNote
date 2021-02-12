package app.elite.mynote.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "note_description") val description: String,
    @ColumnInfo(name = "note_title") val title: String,
    @ColumnInfo(name = "note_create_at") val createAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "note_synchronized_at") val synchronizedAt: Long = 0,
    @ColumnInfo(name = "note_synchronized") val isSynchronized: Boolean = false
)