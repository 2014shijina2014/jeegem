package com.jeegem.common.utils;

import java.io.File;
import java.io.FileFilter;
/*import java.io.FileInputStream;
import java.io.FileOutputStream;*/
import java.io.IOException;
/*import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;*/
import java.net.URL;
/*import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;*/

import org.apache.commons.lang3.StringUtils;

public class FileUtil {

    private static final char COLON_CHAR = ':';
    private static final String UNC_PREFIX = "//";
    private static final String SLASH = "/";
    private static final char SLASH_CHAR = '/';
    private static final char BACKSLASH_CHAR = '\\';
    
    /** 当前目录记号："." */
    public static final String CURRENT_DIR = ".";

    /** 上级目录记号：".." */
    public static final String UP_LEVEL_DIR = "..";

    /**
     * 扩展名分隔符
     */
    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * UNIX文件路径分隔符
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * WINDOWS文件路径分隔符
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /** 临时文件前缀 */
    private static final String TEMP_FILE_PREFIX = "temp_file_prefix-";

    /**
     * 从URL中取得文件，如果URL为空，或不代表一个文件, 则返回null
     * 
     * @param url URL
     * 
     * @return 文件, 如果URL为空，或不代表一个文件, 则返回null
     */
    public static File toFile(URL url) {
        if (url == null) {
            return null;
        }

        if (!"file".equals(url.getProtocol())) {
            return null;
        }

        String path = url.getPath();

        return (path != null) ? new File(path) : null;

    }

    /**
     * 判断文件是否存在，如果path为null，则返回false
     * 
     * @param path 文件路径
     * @return 如果存在返回true
     */
    public static boolean exist(String path) {
        return (path == null) ? false : new File(path).exists();
    }

    /**
     * 判断文件是否存在，如果file为null，则返回false
     * 
     * @param file 文件
     * @return 如果存在返回true
     */
    public static boolean exist(File file) {
        return (file == null) ? false : file.exists();
    }

    /**
     * 是否存在匹配文件
     * 
     * @param directory 文件夹路径
     * @param regexp 文件夹中所包含文件名的正则表达式
     * @return 如果存在匹配文件返回true
     */
    public static boolean exist(String directory, String regexp) {
        File file = new File(directory);
        if (!file.exists()) {
            return false;
        }

        String[] fileList = file.list();
        if (fileList == null) {
            return false;
        }

        for (String fileName : fileList) {
            if (fileName.matches(regexp)) {
                return true;
            }

        }
        return false;
    }

    /**
     * 判断是否为目录，如果path为null，则返回false
     * 
     * @param path 文件路径
     * @return 如果为目录true
     */
    public static boolean isDirectory(String path) {
        return (path == null) ? false : new File(path).isDirectory();
    }

    /**
     * 判断是否为目录，如果file为null，则返回false
     * 
     * @param file 文件
     * @return 如果为目录true
     */
    public static boolean isDirectory(File file) {
        return (file == null) ? false : file.isDirectory();
    }

    /**
     * 判断是否为文件，如果path为null，则返回false
     * 
     * @param path 文件路径
     * @return 如果为文件true
     */
    public static boolean isFile(String path) {
        return (path == null) ? false : new File(path).isDirectory();
    }

    /**
     * 判断是否为文件，如果file为null，则返回false
     * 
     * @param file 文件
     * @return 如果为文件true
     */
    public static boolean isFile(File file) {
        return (file == null) ? false : file.isDirectory();
    }
    
    /**
     * 列出文件目录dir下以suffix结尾的子文件集合，非递归
     * <p>
     * 如果dir为null或不存在，则返回null
     * <p>
     * 如果dir不为目录，则返回null
     * <p>
     * 如果 suffix为null或""，则代表返回所有子文件
     * 
     * @param dir 文件目录
     * @param suffix 文件后缀
     * @return 目录dir下以suffix结尾的子文件集合，非递归
     */
    public static File[] listDirSuffixFiles(File dir, final String suffix) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists() || dir.isFile()) {
            return null;
        }

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return StringUtils.isEmpty(suffix) ? true : (file.getName().endsWith(suffix));
            }
        });
    }
    
    /**
     * 列出文件目录dirPath下以suffix结尾的子文件集合，非递归
     * <p>
     * 如果dirPath为null或不存在，则返回null
     * <p>
     * 如果dirPath不为目录，则返回null
     * <p>
     * 如果 suffix为null或""，则代表返回所有子文件
     * 
     * @param dirPath 文件目录
     * @param suffix 文件后缀
     * @return 目录dirPath下以suffix结尾的子文件集合，非递归
     */
    public static File[] listDirSuffixFiles(String dirPath, final String suffix) {
        if (!exist(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return StringUtils.isEmpty(suffix) ? true : (file.getName().endsWith(suffix));
            }
        });
    }

    /**
     * 列出文件目录dir下满足所有条件conditions的子文件集合，非递归
     * <p>
     * 如果dir为null或不存在，则返回null
     * <p>
     * 如果dir不为目录，则返回null
     * <p>
     * 如果conditions为null，则认为无条件限制
     * 
     * @param dir 文件目录
     * @param conditions 过滤条件
     * @return 目录dir下满足所有条件conditions的子文件集合，非递归
     */
    public static File[] listDirAllConditionFiles(File dir, final boolean...conditions) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists() || dir.isFile()) {
            return null;
        }

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (!condition) {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
     * 列出文件目录dirPath下满足所有条件conditions的子文件集合，非递归
     * <p>
     * 如果dirPath为null或不存在，则返回null
     * <p>
     * 如果dirPath不为目录，则返回null
     * <p>
     * 如果conditions为null，则认为无条件限制
     * 
     * @param dirPath 文件目录
     * @param conditions 过滤条件
     * @return 目录dirPath下满足所有条件conditions的子文件集合，非递归
     */
    public static File[] listDirAllConditionFiles(String dirPath, final boolean...conditions) {
        if (!exist(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (!condition) {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
     * 列出文件目录dir下满足任一条件conditions的子文件集合，非递归
     * <p>
     * 如果dir为null或不存在，则返回null
     * <p>
     * 如果dir不为目录，则返回null
     * <p>
     * 如果conditions为null，则认为无条件限制
     * 
     * @param dir 文件目录
     * @param conditions 过滤条件
     * @return 目录dir下满足任一条件conditions的子文件集合，非递归
     */
    public static File[] listDirAnyConditionFiles(File dir, final boolean...conditions) {
        if (dir == null) {
            return null;
        }
        if (!dir.exists() || dir.isFile()) {
            return null;
        }

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (condition) {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
     * 列出文件目录dirPath下满足任一条件conditions的子文件集合，非递归
     * <p>
     * 如果dirPath为null或不存在，则返回null
     * <p>
     * 如果dirPath不为目录，则返回null
     * <p>
     * 如果conditions为null，则认为无条件限制
     * 
     * @param dirPath 文件目录
     * @param conditions 过滤条件
     * @return 目录dirPath下满足任一条件conditions的子文件集合，非递归
     */
    public static File[] listDirAnyConditionFiles(String dirPath, final boolean...conditions) {
        if (!exist(dirPath)) {
            return null;
        }
        File dir = new File(dirPath);

        return dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (null == conditions || conditions.length == 0) {
                    return true;
                }
                for (boolean condition : conditions) {
                    if (condition) {
                        return true;
                    }
                }

                return false;
            }
        });
    }
    
    /**
     * 简单工厂
     * 
     * @param filename 文件名
     * @return new File(filename)
     */
    public static File file(String filename) {
        if (filename == null) {
            return null;
        }
        return new File(filename);
    }

    /**
     * 简单工厂
     * 
     * @param parent 父目录
     * @param child 子文件
     * @return new File(parent, child);
     */
    public static File file(File parent, String child) {
        if (child == null) {
            return null;
        }

        return new File(parent, child);
    }

    /**
     * 创建文件，不管文件层级，均可创建
     * 
     * @param path 文件路径
     * @return 是否创建成功，如果path为空或者path为null ,则返回false
     * @throws IOException
     */
    public static boolean createFile(String path) throws IOException {
        return createFile(path, false);
    }

    /**
     * 创建文件，不管文件层级，均可创建
     * 
     * @param path 文件路径
     * @param override 是否覆盖
     * @return 是否创建成功，如果path为空或者path为null ,则返回false
     * @throws IOException
     */
    public static boolean createFile(String path, boolean override) throws IOException {
        if (path == null) {
            return false;
        }

        File file = new File(path);
        if (file.exists() && !override) {
            return false;
        }

        if (file.isDirectory()) {
            return file.mkdirs();
        }

        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        return file.createNewFile();
    }

    /**
     * 创建文件夹，不管文件层级，均可创建
     * 
     * @param path 文件路径
     * @param override 是否覆盖
     * @return 是否创建成功，如果path为空或者path为null ,则返回false
     */
    public static boolean createDir(String path, boolean override) {
        if (path == null) {
            return false;
        }

        File file = new File(path);
        if (file.exists() && !override) {
            return false;
        }

        return file.mkdirs();
    }

    /**
     * 创建文件夹，不管文件层级，均可创建
     * 
     * @param path 文件路径
     * @return 是否创建成功，如果path为空或者path为null ,则返回false
     */
    public static boolean createDir(String path) {
        return createDir(path, false);
    }

    /**
     * 创建文件路径的父文件夹，不管文件层级，均可创建
     * 
     * @param path 文件路径
     * @return 是否创建成功，如果path为空或者path为null ,则返回false
     */
    public static boolean createParentDir(String path) {
        return createParentDir(path, false);
    }

    public static boolean createParentDir(File file) {
        return createParentDir(file, false);
    }

    /**
     * 创建文件路径的父文件夹，不管文件层级，均可创建
     * 
     * @param path 文件路径
     * @param override 是否覆盖
     * @return 是否创建成功，如果path为空或者path为null ,则返回false
     */
    public static boolean createParentDir(String path, boolean override) {
        if (path == null) {
            return false;
        }

        return createDir(new File(path).getParent(), override);
    }

    public static boolean createParentDir(File file, boolean override) {
        if (file == null) {
            return false;
        }

        return createDir(file.getParent(), override);
    }

    /**
     * 刪除文件
     * 
     * @param file 文件
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }

        return file.delete();
    }
}
