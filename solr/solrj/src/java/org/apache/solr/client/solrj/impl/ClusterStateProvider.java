/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.solr.client.solrj.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Set;

import org.apache.solr.common.cloud.ClusterState;

public interface ClusterStateProvider extends Closeable {

  /**
   * Obtain the state of the collection (cluster status).
   * @return the collection state, or null is collection doesn't exist
   */
  ClusterState.CollectionRef getState(String collection);

  /**
   * Obtain set of live_nodes for the cluster.
   */
  Set<String> getLiveNodes();

  /**
   * Given a collection alias, returns a list of collections it points to, or returns a singleton list of the input if
   * it's not an alias.
   */
  List<String> resolveAlias(String alias);

  /**
   * Obtain the current cluster state.
   */
  ClusterState getClusterState() throws IOException;

  /**
   * Obtain cluster properties.
   * @return configured cluster properties, or an empty map, never null.
   */
  Map<String, Object> getClusterProperties();

  /**
   * Obtain a cluster property, or the default value if it doesn't exist.
   */
  default <T> T getClusterProperty(String key, T defaultValue) {
    T value = (T) getClusterProperties().get(key);
    if (value == null)
      return defaultValue;
    return value;
  }

  /**
   * Obtain a cluster property, or null if it doesn't exist.
   */
  default <T> T getClusterProperty(String propertyName) {
    return (T) getClusterProperties().get(propertyName);
  }

  /**
   * Get the collection-specific policy
   */
  String getPolicyNameByCollection(String coll);

  void connect();
}