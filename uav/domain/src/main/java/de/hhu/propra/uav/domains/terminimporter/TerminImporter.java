package de.hhu.propra.uav.domains.terminimporter;

import java.io.InputStream;
import java.util.List;

public interface TerminImporter {

  List<TerminFileDTO> convertToTerminFile(InputStream inputStream);
}
