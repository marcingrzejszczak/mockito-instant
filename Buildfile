repositories.remote << 'http://repo1.maven.org/maven2'

define 'mockito-starter' do
  project.group = 'com.blogspot.toomuchcoding'
  project.version = '1.0'
  test.with 'org.mockito:mockito-all:jar:1.9.5', 'com.googlecode.catch-exception:catch-exception:jar:1.2.0'
  package :jar
end
