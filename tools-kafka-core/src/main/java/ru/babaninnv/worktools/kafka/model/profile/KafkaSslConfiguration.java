package ru.babaninnv.worktools.kafka.model.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaSslConfiguration {

    /**
     * <b>ssl.enabled.protocols</b>
     * <br/>
     * <p>The list of protocols enabled for SSL connections.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	TLSv1.2</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String sslEnabledProtocols;

    /**
     * <b>ssl.keystore.type</b>
     * <br/>
     * <p>The file format of the key store file. This is optional for client.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	JKS</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String sslKeystoreType = "JKS";

    /**
     * <b>ssl.protocol</b>
     * <br/>
     * <p>The SSL protocol used to generate the SSLContext. Default setting is TLSv1.2, which is fine for most cases. Allowed values in recent JVMs are TLSv1.2 and TLSv1.3. TLS, TLSv1.1, SSL, SSLv2 and SSLv3 may be supported in older JVMs, but their usage is discouraged due to known security vulnerabilities.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	TLSv1.2</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String sslProtocol;

    /**
     * <b>ssl.provider</b>
     * <br/>
     * <p>The name of the security provider used for SSL connections. Default value is the default security provider of the JVM.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String sslProvider;

    /**
     * <b>ssl.truststore.type</b>
     * <br/>
     * <p>The file format of the trust store file.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	JKS</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	medium</li>
     * </ul>
     */
    private String sslTruststoreType = "JKS";

    /**
     * <b>ssl.key.password</b>
     * <br/>
     * <p>The password of the private key in the key store file. This is optional for client.</p>
     * <ul>
     *   <li>Type:	password</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String sslKeyPassword;

    /**
     * <b>ssl.keystore.location</b>
     * <br/>
     * <p>The location of the key store file. This is optional for client and can be used for two-way authentication for client.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String sslKeystoreLocation;

    /**
     * <b>ssl.keystore.password</b>
     * <br/>
     * <p>The store password for the key store file. This is optional for client and only needed if ssl.keystore.location is configured.</p>
     * <ul>
     *   <li>Type:	password</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String sslKeystorePassword;

    /**
     * <b>ssl.truststore.location</b>
     * <br/>
     * <p>The location of the trust store file.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String sslTruststoreLocation;

    /**
     * <b>ssl.truststore.password</b>
     * <br/>
     * <p>The password for the trust store file. If a password is not set access to the truststore is still available, but integrity checking is disabled.</p>
     * <ul>
     *   <li>Type:	password</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	high</li>
     * </ul>
     */
    private String sslTruststorePassword;

    /**
     * <b>ssl.cipher.suites</b>
     * <br/>
     * <p>A list of cipher suites. This is a named combination of authentication, encryption, MAC and key exchange algorithm used to negotiate the security settings for a network connection using TLS or SSL network protocol. By default all the available cipher suites are supported.</p>
     * <ul>
     *   <li>Type:	list</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String sslCipherSuites;

    /**
     * ssl.client.auth
     * Configures kafka broker to request client authentication. The following settings are common:
     * <p>
     * <b>ssl.client.auth=required If set to required client authentication is required.</b>
     * <br/>
     * ssl.client.auth=requested This means client authentication is optional. unlike requested , if this option is set client can choose not to provide authentication information about itself
     * sslulclient.auth=none This means client authentication is not needed.
     *   <li>Type:	string</li>
     *   De<li>fault:	none</li>
     *   De<lValid Values:</li>
     *   De<lImportance:	low</li>
     * </ul>
     * <b>ssl.endpoint.identification.algorithm</b>
     * <br/>
     * <p>The endpoint identification algorithm to validate server hostname using server certificate.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	https</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String sslClientAuth;

    /**
     * <b>ssl.keymanager.algorithm</b>
     * <br/>
     * <p>The algorithm used by key manager factory for SSL connections. Default value is the key manager factory algorithm configured for the Java Virtual Machine.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	SunX509</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String sslKeymanagerAlgorithm;

    /**
     * <b>ssl.secure.random.implementation</b>
     * <br/>
     * <p>The SecureRandom PRNG implementation to use for SSL cryptography operations.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	null</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String sslSecureRandomImplementation;

    /**
     * <b>ssl.trustmanager.algorithm</b>
     * <br/>
     * <p>The algorithm used by trust manager factory for SSL connections. Default value is the trust manager factory algorithm configured for the Java Virtual Machine.</p>
     * <ul>
     *   <li>Type:	string</li>
     *   <li>Default:	PKIX</li>
     *   <li>Valid Values:</li>
     *   <li>Importance:	low</li>
     * </ul>
     */
    private String sslTrustmanagerAlgorithm;
}
