package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.Query
import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlPreparedStatement
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * No-op SqlDriver for wasmJs - database operations will not persist.
 * TODO: Implement with WebWorkerDriver when browser APIs are available in Kotlin/Wasm
 */
private class NoOpSqlDriver : SqlDriver {
    override fun <R> executeQuery(
        identifier: Int?,
        sql: String,
        mapper: (SqlCursor) -> QueryResult<R>,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ): QueryResult<R> {
        return mapper(NoOpCursor)
    }

    override fun execute(
        identifier: Int?,
        sql: String,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ): QueryResult<Long> {
        return QueryResult.Value(0L)
    }

    override fun newTransaction(): QueryResult<Transacter.Transaction> {
        return QueryResult.Value(NoOpTransaction)
    }

    override fun currentTransaction(): Transacter.Transaction? = null

    override fun addListener(vararg queryKeys: String, listener: Query.Listener) {}
    override fun removeListener(vararg queryKeys: String, listener: Query.Listener) {}
    override fun notifyListeners(vararg queryKeys: String) {}
    override fun close() {}
}

private object NoOpCursor : SqlCursor {
    override fun next(): QueryResult<Boolean> = QueryResult.Value(false)
    override fun getString(index: Int): String? = null
    override fun getLong(index: Int): Long? = null
    override fun getDouble(index: Int): Double? = null
    override fun getBytes(index: Int): ByteArray? = null
    override fun getBoolean(index: Int): Boolean? = null
}

private object NoOpTransaction : Transacter.Transaction() {
    override val enclosingTransaction: Transacter.Transaction? = null
    override fun endTransaction(successful: Boolean): QueryResult<Unit> = QueryResult.Value(Unit)
}

actual fun sqlDelightDriverFactoryModule(): Module = module {
    factory<SqlDriver> { NoOpSqlDriver() }
}
