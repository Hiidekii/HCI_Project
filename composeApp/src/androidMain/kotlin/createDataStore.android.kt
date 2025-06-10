import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ulima.hci_project_g2.core.data.DATA_STORE_FILE_NAME

fun createDataStore(context: Context): DataStore<Preferences> {
    return com.ulima.hci_project_g2.core.data.createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}