package nano.ggModules;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GoogleStorageModule {
	
	private static final String BUCKETNAME = "mypcbucket";
	
	private  Storage getStorage() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("key.json").getFile());
		Storage storage = StorageOptions.newBuilder()
			    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(file)))
			    .build()
			    .getService();
		return storage;
	}
	
	public static String upload(String fileName, byte[] content, String fileType) throws IOException {
		GoogleStorageModule module = new GoogleStorageModule();
		Storage storage = module.getStorage();
		BlobId blobId = BlobId.of(BUCKETNAME, fileName);
		List<Acl> acl = new ArrayList<Acl>();
		acl.add( Acl.of(User.ofAllUsers(), Role.READER));
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setAcl(acl).setContentType(fileType).build();
		storage.create(blobInfo, content);
		return "https://storage.googleapis.com/" + BUCKETNAME + "/" + fileName;
	}
	public static void delete(String fileName) throws IOException {
		GoogleStorageModule module = new GoogleStorageModule();
		Storage storage = module.getStorage();
		BlobId blobId = BlobId.of(BUCKETNAME, fileName);
		storage.delete(blobId);
	}
}
